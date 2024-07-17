# epost-openapi-proxy

## Abstract

## Routes

| service                                           | route id                                  | notes |
|---------------------------------------------------|-------------------------------------------|-------|
| [통합검색 5자리 우편번호 조회서비스][과학기술정보통신부 우정사업본부_우편번호 정보조회] | `retrieve_new_adress_area_cd_search_all_service` |       |      
| [새주소 5자리 우편번호 조회서비스][과학기술정보통신부 우정사업본부_도로명주소조회서비스] | `retrieve_new_adress_area_cd_service`          |       |     

## How to

### `@SpringBootApplication`

### `application.(properties|yaml)`

## Links

### [github.com/spring-cloud/spring-cloud-gateway](https://github.com/spring-cloud/spring-cloud-gateway)

* [Double encoded URLs](https://github.com/spring-cloud/spring-cloud-gateway/issues/2065) (issues/2065)
* [Route Configuration Not Merging from Imported YAML Files](https://github.com/spring-cloud/spring-cloud-gateway/issues/3098)

[과학기술정보통신부 우정사업본부_우편번호 정보조회]: https://www.data.go.kr/data/15056971/openapi.do

[과학기술정보통신부 우정사업본부_도로명주소조회서비스]: https://www.data.go.kr/data/15000124/openapi.do
