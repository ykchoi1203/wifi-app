## Project - Wifi-app
- 서울 공공 데이터 API를 이용해 자신의 위치 또는 특정 위치에서 가장 가까운 공공 와이파이 20개를 찾는 프로젝트

## Stack
- Language : `Java`
- Build : `Maven`
- Database : `SQLITE`
- Server : `Tomcat 8.5`
- JDK : `JDK 1.8`
- Web : `CSS`, `HTML5`, `JSP`
- Library : `Lombok`, `Okhttp3`, `Gson`


## 프로젝트 기능
- OPEN API를 활용하여 서울시의 공공 와이파이 정보를 가져옵니다.
- 사용자의 위치 좌표 또는 특정 위치 좌표를 통해 가장 가까운 공공 와이파이 정보 20개를 보여줍니다.
- 사용자가 입력한 위치 정보와 조회한 시점을 기준으로 DB에 정보를 저장하고, 저장된 정보를 조회할 수 있습니다.
- 공공 와이파이의 상세 정보를 제공합니다.
- 사용자는 북마크 그룹을 생성할 수 있고, 그룹 목록을 확인할 수 있으며, 그룹을 수정하거나 삭제할 수 있습니다.
- 사용자는 공공 와이파이 정보를 원하는 북마크 그룹 안에 북마크로 추가하거나 삭제할 수 있습니다.
- 북마크 그룹을 삭제하면 해당 북마크 그룹에 포함된 북마크들도 일괄 삭제됩니다.
