Controller中接受前端中的整型数据,用Integer接收。
    在控制层中，通常情况下，如果你不需要处理 null 值，直接使用 int 就足够了。
    如果你需要处理 null 值，或者希望在参数中接受 null 值，可以使用 Integer。

制作java项目:最好使用springboot2+jdk8等等低版本（高版本有许多东西可能使用不了）
制作vue项目:最好使用vue2版本（vue3版本有些东西不好使用）

swagger访问地址：http://localhost:8099/api/swagger-ui/index.html#/

MySql数据库为Docker中的容器：mysql 3309 8.0.37

swagger(放行路径):
    "/swagger-ui/**",
    "/webjars/**",
    "/v3/api-docs/**",
    "/v3/api-docs.yaml"