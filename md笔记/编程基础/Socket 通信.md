# Socket 通信

本地的进程间通信方式有很多种，不同进程之间可以通过进程号来区分不同的进程。那在网络中通信的进程又是如何唯一标识一个进程呢？

根据 TCP/ IP 协议簇的定义，网络层的 **IP 地址**可以唯一标识网络中的主机，传输层的**协议+端口**可以唯一标识主机中的应用程序（进程），因此利用三元组（IP 地址，协议，端口）就可以标识网络中的进程了，而网络中的进程通信就可以利用这个标志实现进程间交互。

使用 TCP / IP 协议的应用程序通常采用应用编程接口来实现网络进程间的通信，目前几乎所有的应用程序都是采用 socket （套接字）来进行通信。socket 起源于 Unix，而 Unix / Linux 的基本哲学之一就是“一切皆文件”，都可以通过”open，read / write，close“模式来操作。因此 socket 也可以理解成是一种特殊的文件，它对应的接口函数就是实现对其进行读写、打开、关闭等操作。



## socket 的基本操作

### socket（）函数

```c
int socket(int domain, int type, int protocol);
```

socket（）函数相当于一般的文件打开操作，函数返回一个 socket 描述符，用于唯一标识一个 socket。该函数的入参分别为：

* **domain**：协议域，又称协议族（family）。协议族决定了 socket 的地址类型。常用的协议族有 AF_INET、AF_INET6、AF_LOCAL、AF_ROUTE 等。
* **type**：指定 socket 类型。常用的 socket 类型有 SOCK_STREAM、SOCK_DGRAM、SCK_RAW、SOCK_PACKET、SOCK_SEQPACKET 等。
* **protocol**：指定 socket 使用的协议。常用的协议有 IPPROTO_TCP、TPPROTO_UDP、IPPROTO_SCTP、IPPROTO_TIPC 等。

以上的 type 和 protocol 并不能随意组合，必须遵照相应协议的特点来选用。当 protocol 为 0 时，会自动匹配 type 对应的默认协议。

当我们调用 **socket（）** 创建一个 socket 时，返回的 socket 描述符存在于**协议族（address family，AF_XXX）空间**中，但没有一个具体的地址。如果想要给它赋值一个地址，就必须调用 bind（） 函数，否则当调用connect（）、listen（）时系统会自动随机分配一个端口。



### bind（）函数

```c
int bind(int sockfd, const struct sockaddr *addr, socklen_t addrlen);
```

正如上面提到的，bind（）函数把一个**地址族**中的特定地址赋给 socket，例如对应 AF_INET、AF_INET6 就是把一个 **IPv4 或 IPv6 地址和端口号组合**赋给 socket。函数入参分别为：

* sockfd：socket 描述符。
* addr：一个指针，指向要绑定给 socket 的协议地址。这个地址的结构根据创建 socket 时的地址协议族的不同而不同，常用的结构有对应 IPv4、IPv6、Unix 域等。
* addrlen：对应地址的长度。

通常服务器在启动时都会绑定一个众所周知的地址（如 IP 地址 + 端口号），用于接收客户的服务请求，用户就可以通过向它发送请求来连接服务器；而客户端就不用指定，有系统自动分配的一个端口和自身的 IP 地址组合作为标识。这就是为什么通常服务器端在调用 listen（）之前会先调用 bind（）绑定地址，而客户端就不用，而是在调用 connect（）时由系统随机生成一个。



### listen（）函数、connect（）函数

```c
int listen(int sockfd, int backlog);
int connect(int sockfd, const struct sockaddr *addr, socklen_t addrlen);
```

作为一个服务器，在调用 socket（）、bind（）之后就会调用 listen（）来监听这个 socket，如果此时客户端调用 connect（）发出连接请求，服务器端就会接收到这个请求。

在 listen（）函数中，第一个参数 sockfd 即为要监听的 socket 描述符，第二个参数 backlog 为该 socket 可以排队的最大连接个数。socket（）函数创建的 socket **默认是一个主动类型**的，listen（）函数将 socket 变为被动类型的，等待客户的连接请求。

在 connect（）函数中，第一个参数 sockfd 即为客户端的 socket 描述符，第二个参数 addr 为想要请求连接的服务器的 socket 地址，第三个参数 addrlen 为 socket 地址的长度。客户端通过调用 connect（）函数来建立与 TCP 服务器的连接。



### accept（）函数

TCP 服务器端依次调用 socket（）、bind（）、listen（）之后，就会监听指定的 socket 地址了。客户端依次调用 socket（）、connect（）之后就向 TCP 服务器发送了一个连接请求。TCP 服务器监听到这个请求之后，就会调用 accept（） 函数取接收请求，这样连接就建立好了。之后就可以开始网络 I/O 操作了，即类同于普通文件的读写 I/O 操作。

```c
int accept(int sockfd, struct sockaddr *addr, socklen_t *addrlen);
```

在 accept（）函数中，第一个参数 sockfd 为服务器的 socket 描述符，第二个参数 addr 用于**返回客户端的协议地址**，第三个参数 addrlen 为协议地址的长度。如果 accpet 成功，那么其返回值是由内核自动生成的一个**全新的描述符**，代表用于与客户端通信的 TCP 连接。

这里需要重点理解的是，accept（）函数的第一个参数为服务器用于监听请求的 socket 描述符，是服务器开始调用 socket（）函数生成的，称为监听 socket 描述符；而 accept（）函数返回的是已建立连接的 socket 描述符。一个服务器通常通常仅仅**只创建一个监听 socket 描述符**，它在该服务器的生命周期内一直存在。内核为每个由服务器进程接受的客户连接创建了一个已连接 socket 描述符，当服务器完成了对某个客户的服务，相应的已连接socket 描述符就被关闭。



### socket 中的读写

网络 socket 通信常用的读写 I/O 操作有：

* read（）/ write（）
* recv（）/ send（）
* readv（）/ writev（）
* recvmsg（）/ sendmsg（）
* recvfrom（）/ sendto（）

这里会推荐使用 recvmsg（）/ sendmsg（）函数，这两个函数是最通用的 I/O 函数，实际上可以使用他们替换上面其他的函数进行使用。

read 函数是负责从 fd 中读取内容。当读成功时，read **返回实际所读的字节数**，如果返回的值是 0 表示已经读到文件的结束了，小于 0 表示出现了错误。如果错误为 EINTR 说明读是由中断引起的，如果是 ECONNREST 表示网络连接出了问题。

write 函数将 buf 中的 nbytes 字节内容写入描述符 fd，成功时**返回写的字节数**，失败时返回 -1，并设置 errno 变量。 在网络程序中，当我们向套接字描述符进行写操作时有两种可能。

1. write 的返回值大于 0，表示写了部分或者是全部的数据。
2. 返回的值小于 0，此时出现了错误。我们要根据错误类型来处理。如果错误为 EINTR 表示在写的时候出现了中断错误。如果为 EPIPE 表示网络连接出现了问题（如对方已经关闭了连接）。



### close（）函数

在服务器与客户端建立连接之后，会进行一些读写操作，完成了读写操作就要关闭相应的 socket 描述符，好比操作完打开的文件要调用 fclose（）关闭打开的文件。

```c
#include <unistd.h>
int close(int fd);
```

close 一个 TCP socket 的缺省行为时把该 socket 标记为以关闭，然后立即返回到调用进程。该描述符不能再由调用进程使用，也就是说不能再作为 read 或 write 的第一个参数。

需要注意的是，close 操作只是使相应 socket 描述符的引用计数置为 -1，只有当引用计数为 0 的时候，才会触发客户端向服务器发送终止连接请求。



## socket 中实现 TCP 三次握手

建立 TCP 连接需要“三次握手”，即交换三个分组，其大致流程如下：

* 客户端向服务器端发送一个 SYN_J。
* 服务器端向客户端响应一个 SYN_K，并对 SYN_x 进行确认返回 ACK_J+1。
* 客户端对 SYN_y 进行确认，返回一个 ACK_K+1。

在三次握手中，对应调用的 socket 函数如下图所示：

![img](https://images.cnblogs.com/cnblogs_com/skynet/201012/201012122157467258.png)

从图中可以看出，

* 当客户端调用 connect（）时，触发了连接请求，向服务器发送了 SYN_J 包，这时 connect（）函数进入阻塞状态，等待响应；
* 服务器监听到连接请求，即收到 SYN_J 包，调用 accept（）函数接收请求向客户端发送 SYN_K ，ACK_J+1，这时 accept（）进入阻塞状态，等待客户端 ACK 回复；
* 客户端收到服务器的 SYN_K ，ACK_J+1之后，这时 connect（）返回，并对 SYN_K 进行确认；
* 服务器收到 ACK_K+1时，accept（）返回，至此三次握手完毕，连接建立。

总结起来就是：客户端的 connect（）在三次握手的第二次返回，而服务器端的 accept（）在三次握手的第三次返回。

