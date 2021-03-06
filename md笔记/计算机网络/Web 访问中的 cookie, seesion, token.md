# Web 访问中的 cookie, seesion, token

### cookie

HTTP cookie（也叫 Web Cookie 或浏览器 Cookie）是服务器发送到用户浏览器并保存在本地的一小块数据，它会在浏览器下次向同一服务器再发起请求时被携带并发送到服务器上。cookie 通常保存在客户端浏览器上，有时候也会保存在服务端。单个 cookie 保存的数据一般小于 4 KB，一个站点最多保存 20 个 cookie。它只能保管 ASCII 字符串，需要通过编码的方式存取 Unicode 字符或者二进制数据，因此难以利用 cookie 实现存储略微复杂的信息。

#### 保存用户密码或者用户喜好

当我们登录网站勾选保存用户名和密码的时候，一般保存的都是cookie，将用户名和密码的cookie保存到硬盘中，这样再次登录的时候浏览器直接将cookie发送到服务端验证。相比于直接将用户名和密码保存到客户端中，这样的操作更加安全，浏览器也可以通过加密解密实现安全通信。用户使用浏览器时的偏好的设置，也可以通过 cookie 保存到客户端，这样用户登录浏览器以后就可以直接拿到相应的偏好设置。

#### 跟踪会话

有时候一些网站的网页有不同的访问权限，有些只允许登录的用户进行访问，或者用户级别不同的不能访问。但是 http 请求是无状态的，每次访问服务端时都不知道请求方是否为登录用户，所以在 http 请求报文中加入登录标识就可以了，这个**登录标识**就是 cookie，这样服务器端就能通过请求报文拿到登录标识 cookie，进行比较后就可以确定会话身份了。

这些 cookie 是为了实现跟踪会话的目的，所以客户端有，服务端也有，而且服务端会有全部的会话 cookie。可以通过设置 cookie 的属性，达到 cookie 长期有效的效果。



### session

Session 代表着服务器和客户端一次会话的过程。session 对象存储特定用户会话所需的属性及配置信息。这样，当用户在应用程序的 web 页之间跳转时，存储在 session 对象中的变量将不会丢失，而是在整个用户会话中一直存在下去。当客户端关闭会话，或者 session 超时失效时会话结束。

session 技术是后来衍生出来的，也要使用到 cookie，通常保存在服务器端，用于跟踪会话。session 的数量通常没有上限，但出于对服务器端的性能考虑，session 内不要存放过多的东西，并且设置了 session 删除机制。

它的出现，主要是考虑到安全问题。由于 http 是无状态的协议，所以用户每次读取 web 页面时，服务器都会打开新的会话，而且服务器不会自动维护客户的上下文信息。session 就是一种保存上下文信息的机制，它针对每一个客户，通过 seesionID 区分不同的客户。它以 cookie 或者 url 重写为基础，默认使用 cookie 来实现，与 cookie 不同的是，它是存储在浏览器内存中，而不是写到硬盘上的。



### session 与 cookie 的关系

用户第一次请求服务器的时候，服务器根据用户提交的相关信息，创建创建对应的 Session ，请求返回时将此 Session 的唯一标识信息 SessionID 返回给浏览器，浏览器接收到服务器返回的 SessionID 信息后，会将此信息存入到 Cookie 中，同时 Cookie 记录此 SessionID 属于哪个域名。

当用户第二次访问服务器的时候，请求会自动判断此域名下是否存在 Cookie 信息，如果存在自动将 Cookie 信息也发送给服务端，服务端会从 Cookie 中获取 SessionID，再根据 SessionID 查找对应的 Session 信息，如果没有找到说明用户没有登录或者登录失效，如果找到 Session 证明用户已经登录可执行后面操作。

根据以上流程可知，SessionID 是连接 Cookie 和 Session 的一道桥梁，大部分系统也是根据此原理来验证用户登录状态。

session 通常称为 session cookie，而 cookie 一般又称为 persistent cookie。前者针对单次会话而言，会话结束那它也随之消失；而后者通常是加密后存储于客户端硬盘上的一段文本信息，可能会遭到 cookie 欺骗以及针对 cookie 的脚本攻击，自然在安全性上有所欠缺。



### token

Token 机制多用于 App 客户端和服务器交互的模式，也可以用于 Web 端做用户状态管理。当浏览器中禁止了 cookie，服务器端就无法再继续根据 cookie 中的信息判断用户是否登录，此时可以通过 token 机制来保障整个机制的正常运转。

Token 的意思是“令牌”，是服务端生成的一串字符串，作为客户端进行请求的一个标识。Token 机制和 Cookie 和 Session 的使用机制比较类似。当用户第一次登录后，服务器根据提交的用户信息生成一个 Token，响应时将 Token 返回给客户端，以后客户端只需带上这个 Token 前来请求数据即可，无需再次登录验证。

token 是服务器端将用户信息经过编码后传给客户端。它具有以下优点：

* 简洁：可以通过 url 或者 post 参数或者是在 http 头参数发送，因为数据量小，传输速度也很快
* 自包含：由于串包含了用户所需要的信息，避免了多次查询数据库
* 因为 Token 是以 json 的形式保存在客户端的，所以 JWT 是跨语言的
* 不需要在服务端保存会话信息，特别适用于分布式微服务