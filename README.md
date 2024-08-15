# epost-openapi-proxy

[![Java CI with Gradle](https://github.com/jinahya/epost-openapi-proxy/actions/workflows/gradle.yml/badge.svg)](https://github.com/jinahya/epost-openapi-proxy/actions/workflows/gradle.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_epost-openapi-proxy&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_epost-openapi-proxy)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_epost-openapi-proxy&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_epost-openapi-proxy)

## Abstract

An application for
proxying [과학기술정보통신부 우정사업본부 APIs](https://www.data.go.kr/tcs/dss/selectDataSetList.do?dType=API&keyword=%EA%B3%BC%ED%95%99%EA%B8%B0%EC%88%A0%EC%A0%95%EB%B3%B4%ED%86%B5%EC%8B%A0%EB%B6%80+%EC%9A%B0%EC%A0%95%EC%82%AC%EC%97%85%EB%B3%B8%EB%B6%80&operator=AND&detailKeyword=&publicDataPk=&recmSe=N&detailText=&relatedKeyword=&commaNotInData=&commaAndData=&commaOrData=&must_not=&tabId=&dataSetCoreTf=&coreDataNm=&sort=&relRadio=&orgFullName=&orgFilter=&org=&orgSearch=&currentPage=1&perPage=10&brm=&instt=&svcType=&kwrdArray=&extsn=&coreDataNmArray=&pblonsipScopeCode=)
using [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway).

### Routes

| api                               | service              | route.id                                         | notes |
|-----------------------------------|----------------------|--------------------------------------------------|-------|
| [과학기술정보통신부 우정사업본부_우편번호 다운로드 서비스]  | 우편번호 DB 다운로드 서비스     | `download_area_code_service`                     |       |      
| [과학기술정보통신부 우정사업본부_집배구 구분코드 조회서비스] | 집배구 구분코드 조회서비스       | `retrieve_deliv_area_cd_service`                 |       |      
| [과학기술정보통신부 우정사업본부_영문우편번호조회서비스]    | 영문 우편번호 조회 서비스       | `retrieve_eng_address_service`                   |       |      
| [과학기술정보통신부 우정사업본부_지번주소조회 서비스]     | 지번주소 5자리 우편번호 조회 서비스 | `retrieve_lot_number_adress_area_cd_service`     |       |      
| [과학기술정보통신부 우정사업본부_우편번호 정보조회]      | 통합검색 5자리 우편번호 조회서비스  | `retrieve_new_adress_area_cd_search_all_service` |       |      
| [과학기술정보통신부 우정사업본부_도로명주소조회서비스]     | 새주소 5자리 우편번호 조회서비스   | `retrieve_new_adress_area_cd_service`            |       |     

## How to build

Copy `/src/test/resources/_application.yaml` into `/src/test/resources/application.yaml` and
set `epost.openapi.proxy.service-key` property.

e.g.

```yaml
epost:
  openapi:
    proxy:
      service-key: ......................==
```

## How to use / extend

### Component-scanning

Add [`com.github.jinahya.openapi.proxy.NoOp.class`](src/main/java/com/github/jinahya/epost/openapi/proxy/_NoOp) to the
component-scanning path.

e.g.

https://github.com/jinahya/epost-openapi-proxy/blob/75b114f36b20a12d1ba93ead76818959c11f5735/src/test/java/com/mycompany/Application.java#L1-L17

### Application properties

See [application.yaml](src/test/resources/application.yaml), [application-development.yaml](src/test/resources/application-development.yaml),
and [application-production.yaml](src/test/resources/application-production.yaml).

#### development

Import `classpath:application-epost-openapi-proxy-development.yaml`.

#### production

Import `classpath:application-epost-openapi-proxy-development.yaml`
and `classpath:application-epost-openapi-proxy-production.yaml`.

### Configuration

You can(or should) override various configuration properties through your own `application.(properties|yaml)` file.

#### Service key

Set `epost.openapi.proxy.service-key` property with an url-**decoded** value.

```yaml
epost:
  openapi:
    proxy:
      service-key: <url-decoded-value>
```

#### Timeouts

See [Http timeouts configuration](https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway/http-timeouts-configuration.html).

##### Global timeouts

> `connect-timeout` must be specified in milliseconds.
>
> `response-timeout` must be specified as a java.time.Duration

e.g.

```yaml
spring:
  cloud:
    gateway:
      httpclient:
        connect-timeout: 1024  # connect-timeout must be specified in milliseconds.
        response-timeout: PT4S # response-timeout must be specified as a java.time.Duration
```

##### Per-route timeouts

> connect-timeout must be specified in milliseconds.
>
> response-timeout must be specified in milliseconds.

Set with `epost.openapi.proxy.routes.<route-id>.(connect-timeout|response-timeout)`.

```yaml
epost:
  openapi:
    proxy:
      routes:
        _retrieve_new_adress_area_cd_search_all_service:
          connect-timeout: 1024  # connect-timeout must be specified in milliseconds.
          response-timeout: 4096 # response-timeout must be specified in milliseconds.

```

#### Caching

Enable caching by setting `spring.cloud.gateway.filter.local-response-cache.enabled` property with `true`.

e.g.

```yaml
spring:
  cloud:
    gateway:
      filter:
        local-response-cache:
          enabled: true
```

The [LocalResponseCache GatewayFilter Factory](https://docs.spring.io/spring-cloud-gateway/reference/spring-cloud-gateway/gatewayfilter-factories/local-cache-response-filter.html)
is already configured.
> It accepts the first parameter to override the time to expire a cache entry (expressed in `s` for seconds, `m` for
> minutes, and `h` for hours) and a second parameter to set the maximum size of the cache to evict entries for this
> route (`KB`, `MB`, or `GB`).

Override preconfigured values with `epost.openapi.proxy.routes.<route-id>.cache` property.

e.g.

```yaml
epost:
  openapi:
    proxy:
      routes:
        _retrieve_new_adress_area_cd_search_all_service:
          cache: 10m,128MB
```

## Links

### www.epost.go.kr

* [우편번호 DB와 검색기 소개](https://www.epost.go.kr/search/zipcode/cmzcd002k01.jsp)
    * [우편번호 DB파일](https://www.epost.go.kr/search/zipcode/areacdAddressDown.jsp)

### [github.com/spring-cloud/spring-cloud-gateway](https://github.com/spring-cloud/spring-cloud-gateway)

* [Double encoded URLs](https://github.com/spring-cloud/spring-cloud-gateway/issues/2065) (issues/2065)
* [Route Configuration Not Merging from Imported YAML Files](https://github.com/spring-cloud/spring-cloud-gateway/issues/3098)
* [#3466 AddRequestParameter double encodes parameter value](https://github.com/spring-cloud/spring-cloud-gateway/issues/3466)
* [#3474 Forwarding route produces error at the first request](https://github.com/spring-cloud/spring-cloud-gateway/issues/3474)

[과학기술정보통신부 우정사업본부_우편번호 다운로드 서비스]: https://www.data.go.kr/data/15000302/openapi.do

[과학기술정보통신부 우정사업본부_영문우편번호조회서비스]: https://www.data.go.kr/data/15059038/openapi.do

[과학기술정보통신부 우정사업본부_지번주소조회 서비스]: https://www.data.go.kr/data/15000268/openapi.do

[과학기술정보통신부 우정사업본부_우편번호 정보조회]: https://www.data.go.kr/data/15056971/openapi.do

[과학기술정보통신부 우정사업본부_도로명주소조회서비스]: https://www.data.go.kr/data/15000124/openapi.do

[과학기술정보통신부 우정사업본부_집배구 구분코드 조회서비스]: https://www.data.go.kr/data/15057018/openapi.do
