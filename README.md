# the Shipment Service Server 系统设计报告


## jwt
### 项目采用JWT技术来实现身份验证和授权功能。通过JWT生成和解析令牌，以及使用自定义的JWT授权过滤器，实现对请求的安全验证和访问控制。
加入依赖

    <!--jwt依赖-->
    <dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>${jwt.version}</version>
    </dependency>

JwtAuthorizationFilter类

    public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

        public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
            super(authenticationManager);
        }
    
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws IOException, ServletException {
    
            //从Request Header 取出Token
            String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
    
            //Token为空放行
            //如果接下来进入的URL不是公共的地址SpringSecurity会返回403的错误
            if (!JwtTokenUtil.checkToken(token)){
                chain.doFilter(request, response);
                return;
            }
    
            //判断JWT Token是否过期
            if (JwtTokenUtil.isExpiration(token)) {
                ResponseUtil.writeJson(response, new ResponseResult<>(403, "令牌已过期, 请重新登录"));
                return;
            }
    
            //解析token
            String username = JwtTokenUtil.getUsername(token);
            List<String> tokenRoles = JwtTokenUtil.getTokenRoles(token);
            ArrayList<SimpleGrantedAuthority> roles = new ArrayList<>();
            for (String role : tokenRoles) {
                roles.add(new SimpleGrantedAuthority(role));
            }
            //向SpringSecurity的Context中加入认证信息
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(username,null, roles));
    
            super.doFilterInternal(request, response, chain);
        }

    }
JwtAuthorizationFilter类的doFilterInternal方法中的代码使用JWT来进行身份验证和授权。具体来说：
- 从请求的Authorization头部获取JWT令牌(token)。
- 通过JwtTokenUtil工具类验证并解析JWT令牌，包括检查令牌是否有效、是否过期等操作。
- 如果令牌验证通过，将用户名和角色信息添加到Spring Security的上下文中，以便后续的认证和授权操作使用。

JwtTokenUtil工具类中的方法用于JWT令牌的生成、解析和验证。其中包括：
- checkToken方法用于检查令牌是否存在。
- isExpiration方法用于检查令牌是否过期。
- getUsername方法用于从令牌中获取用户名。
- getTokenRoles方法用于从令牌中获取角色信息。

JWT令牌包含了用户身份信息和角色信息，通过令牌进行安全验证和访问控制。

---

## jpa
### 加入依赖

    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

### 依赖注入 AdminRepository：
    @Resource
    private AdminRepository adminRepository;


### 使用 AdminRepository 进行数据库操作

    adminRepository.save(admin);
    adminRepository.findById(id);
    adminRepository.findAdminByEmail(email);
    adminRepository.findAdminByEmailAndPassword(email, password);
    adminRepository.findAll();
    adminRepository.deleteById(id);


## mysql数据库

![img.png](/img/img5.png)

## vue2
### 项目结构
![img.png](/img/img9.png)


## 前端实机演示
- 没有tocken就没有访问权限

![img.png](/img/img.png)

- 登录

![img1.png](/img/img2.png)

- 获取token并加入header,可在前端查看

![img4.png](/img/img4.png)

- 添加车辆业务

![img.png](/img/img6.png)
![img_1.png](/img/img7.png)

- 申请配送

![img.png](/img/img8.png)
