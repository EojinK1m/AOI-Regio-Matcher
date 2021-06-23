# AOI Region Matcher
## 의존성
- Gradle 7.x+
- Java 11+
- Docker

## 실행 방법 

1. clone repository
    > git clone "https://github.com/EojinK1m/AOI-Region-Matcher.git" 

2. 환경변수 설정
    > $ export AOI_REGION_MATCHER_POSTGIS_USERNAME=postgres

    > $ export AOI_REGION_MATCHER_POSTGIS_PASSWORD=postgres

    > $ export AOI_REGION_MATCHER_POSTGIS_URL=postgresql://localhost:5432/postgres

    > $ export AOI_REGION_MATCHER_PORT=8080

3. 프로젝트 빌드 
    > $ gradle jar
4. 실행
    > $ gradle bootRun

### Docker로 Postgis 실행하기 
1. PostGis 실행 
    > $ docker run --name postgis -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgis/postgis
2. Postgis extend설정 

    사용할 database에 접속,
    >CREATE EXTENSION IF NOT EXISTS postgis;

    >CREATE EXTENSION IF NOT EXISTS postgis_topology;

    >CREATE EXTENSION IF NOT EXISTS fuzzystrmatch;

    >CREATE EXTENSION IF NOT EXISTS postgis_tiger_geocoder;
