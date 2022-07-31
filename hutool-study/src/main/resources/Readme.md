# Hutool 工具

### 1.使用 HUtool 工具链接数据库（DB Utild）
一、初始化配置
<br/>
1.引入 Hutool 工具依赖jar
```xml
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>5.7.20</version>
        </dependency>
```
2.在 resource 下新建 db.setting 配置文件
```text
## db.setting文件
[group1]
url = jdbc:mysql://127.0.0.1:3306/mybatisdemo
user = root
pass = root

## 可选配置
# 是否在日志中显示执行的SQL
showSql = true
# 是否格式化显示的SQL
formatSql = true
# 是否显示SQL参数
showParams = true
# 打印SQL的日志等级，默认debug，可以是info、warn、error
sqlLevel = info

# 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
initialSize = 0
# 最大连接池数量
maxActive = 8
# 最小连接池数量
minIdle = 0
# 获取连接时最大等待时间，单位毫秒。配置了maxWait之后， 缺省启用公平锁，并发效率会有所下降， 如果需要可以通过配置useUnfairLock属性为true使用非公平锁。
maxWait = 0
# 是否缓存preparedStatement，也就是PSCache。 PSCache对支持游标的数据库性能提升巨大，比如说oracle。 在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。作者在5.5版本中使用PSCache，通过监控界面发现PSCache有缓存命中率记录， 该应该是支持PSCache。
poolPreparedStatements = false
# 要启用PSCache，必须配置大于0，当大于0时， poolPreparedStatements自动触发修改为true。 在Druid中，不会存在Oracle下PSCache占用内存过多的问题， 可以把这个数值配置大一些，比如说100
maxOpenPreparedStatements = -1
# 用来检测连接是否有效的sql，要求是一个查询语句。 如果validationQuery为null，testOnBorrow、testOnReturn、 testWhileIdle都不会其作用。
validationQuery = SELECT 1
# 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
testOnBorrow = false
# 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
testOnReturn = false
# 建议配置为true，不影响性能，并且保证安全性。 申请连接的时候检测，如果空闲时间大于 timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
testWhileIdle = true
# 有两个含义： 1) Destroy线程会检测连接的间隔时间 2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
timeBetweenEvictionRunsMillis = 60000
# 物理连接初始化的时候执行的sql
connectionInitSqls = SELECT 1
# 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有： 监控统计用的filter:stat  日志用的filter:log4j 防御sql注入的filter:wall
filters = stat
# 类型是List<com.alibaba.druid.filter.Filter>， 如果同时配置了filters和proxyFilters， 是组合关系，并非替换关系
proxyFilters =
```
即可通过 Hutool 工具操作数据库。

### 2、基本使用
```java
public void getUser() throws SQLException {
    //构建查询条件
        //todo:查询列设置失效
    Entity entity = Entity.create("tb_user").setFieldNames(setField()).set("id", new String[]{"1","2"});
    //通过 use 配置多数据源
    List<Entity> result = Db.use("group1").findAll(entity);
    result.stream().forEach(System.out::println);
}
```
set("fieldCode",value) 方法设置参数<br/>
默认为“=”<br/>
传入参数为集合时为"in"