# epost-openapi-proxy

[![Java CI with Maven](https://github.com/jinahya/epost-openapi-proxy/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/epost-openapi-proxy/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_epost-openapi-proxy&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=jinahya_epost-openapi-proxy)

## Abstract

An application for
proxying [과학기술정보통신부 우정사업본부 APIs](https://www.data.go.kr/tcs/dss/selectDataSetList.do?dType=API&keyword=%EA%B3%BC%ED%95%99%EA%B8%B0%EC%88%A0%EC%A0%95%EB%B3%B4%ED%86%B5%EC%8B%A0%EB%B6%80+%EC%9A%B0%EC%A0%95%EC%82%AC%EC%97%85%EB%B3%B8%EB%B6%80&operator=AND&detailKeyword=&publicDataPk=&recmSe=N&detailText=&relatedKeyword=&commaNotInData=&commaAndData=&commaOrData=&must_not=&tabId=&dataSetCoreTf=&coreDataNm=&sort=&relRadio=&orgFullName=&orgFilter=&org=&orgSearch=&currentPage=1&perPage=10&brm=&instt=&svcType=&kwrdArray=&extsn=&coreDataNmArray=&pblonsipScopeCode=)
using [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway).

### Routes

| api              | service              | route.id                                         | notes |
|------------------|----------------------|--------------------------------------------------|-------|
| [우편번호 다운로드 서비스]  | 우편번호 DB 다운로드 서비스     | `download_area_code_service`                     |       |      
| [집배구 구분코드 조회서비스] | 집배구 구분코드 조회서비스       | `retrieve_deliv_area_cd_service`                 |       |      
| [영문우편번호조회서비스]    | 영문 우편번호 조회 서비스       | `retrieve_eng_address_service`                   |       |      
| [지번주소조회 서비스]     | 지번주소 5자리 우편번호 조회 서비스 | `retrieve_lot_number_adress_area_cd_service`     |       |      
| [우편번호 정보조회]      | 통합검색 5자리 우편번호 조회서비스  | `retrieve_new_adress_area_cd_search_all_service` |       |      
| [도로명주소조회서비스]     | 새주소 5자리 우편번호 조회서비스   | `retrieve_new_adress_area_cd_service`            |       |     

## How to build

### How to test

```commandline
$ mvn clean test
```

### How to verify

```commandline
$ SERVICE_KEY=<your-own-URL-DECODED> mvn -Pfailsafe clean verify
```

e.g.

```commandline
$ SERVICE_KEY='...==' mvn -Pfailsafe clean verify
```

## Links

### www.epost.go.kr

* [우편번호 DB와 검색기 소개](https://www.epost.go.kr/search/zipcode/cmzcd002k01.jsp)
    * [우편번호 DB파일](https://www.epost.go.kr/search/zipcode/areacdAddressDown.jsp)


### github.com

* [spring-cloud/spring-cloud-gateway](https://github.com/spring-cloud/spring-cloud-gateway)
  * [Double encoded URLs](https://github.com/spring-cloud/spring-cloud-gateway/issues/2065) (issues/2065)
  * [Route Configuration Not Merging from Imported YAML Files](https://github.com/spring-cloud/spring-cloud-gateway/issues/3098)
  * [#3466 AddRequestParameter double encodes parameter value](https://github.com/spring-cloud/spring-cloud-gateway/issues/3466)
  * [#3474 Forwarding route produces error at the first request](https://github.com/spring-cloud/spring-cloud-gateway/issues/3474)
* [tdf/odftoolkit](https://github.com/tdf/odftoolkit)

### [ODFDOM - The OpenDocument API](https://odftoolkit.org/odfdom/index.html)

### [langintro.com](https://langintro.com/)

* [Creating Spreadsheet Documents Using ODFDOM](https://langintro.com/odfdom_tutorials/create_ods.html)

### https://mimetype.io

* [All MIME types](https://mimetype.io/all-types)

### https://www.iana.org

* [/assignments/media-types/application/zip](https://www.iana.org/assignments/media-types/application/zip)

### stackoverflow.com

* [How to encode the filename parameter of Content-Disposition header in HTTP?](https://stackoverflow.com/q/93551/330457)

### [developers.mozilla.org](https://developer.mozilla.org) ([en_US](https://developer.mozilla.org/en_US/))

* [/docs/Web/HTTP/Headers/Content-Disposition](https://developer.mozilla.org/docs/Web/HTTP/Headers/Content-Disposition) ([en_US](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Disposition))

---

[우편번호 다운로드 서비스]: https://www.data.go.kr/data/15000302/openapi.do

[영문우편번호조회서비스]: https://www.data.go.kr/data/15059038/openapi.do

[지번주소조회 서비스]: https://www.data.go.kr/data/15000268/openapi.do

[우편번호 정보조회]: https://www.data.go.kr/data/15056971/openapi.do

[도로명주소조회서비스]: https://www.data.go.kr/data/15000124/openapi.do

[집배구 구분코드 조회서비스]: https://www.data.go.kr/data/15057018/openapi.do
