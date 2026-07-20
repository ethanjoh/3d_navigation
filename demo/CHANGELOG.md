<!-- 변경 이력을 관리하는 CHANGELOG 문서 -->
# Changelog

## [2026-07-20] - AGV 및 Lab 3D 모델 바이너리 최신화

### 변경 목적
- AGV 및 Laboratory 3D GLB 모델의 최신 메쉬 자산을 웹 뷰어 시스템에 반영.

### 주요 결정 사항
- `demo/agv-exam/agv.glb` 3D 바이너리 자산 업데이트
- `demo/lab-exam/lab.glb` 3D 바이너리 자산 업데이트

### 수정한 파일
- `demo/agv-exam/agv.glb`
- `demo/lab-exam/lab.glb`
- `demo/CHANGELOG.md`

### 테스트 및 검증 결과
- 웹 뷰어 환경에서 최신 3D GLB 모델 정상 로딩 검증 완료

## [2026-07-20] - AGV 클릭 인터랙션 및 3D 궤도 주행 기능 추가

### 변경 목적
- WRL 변환 시 유실되었던 AGV 차량의 궤도 주행 동작을 Three.js `Raycaster` 및 `CatmullRomCurve3` 경로 보간 기술을 활용하여 웹 뷰어 상에서 클릭으로 시작/정지 토글이 가능하도록 구현.

### 주요 결정 사항
- AGV 바운딩 박스를 기준으로 한 파란색 3D 트랙 가이드라인(`LineLoop`) 생성
- 배경 큐브 메쉬(`Shape_Box` 및 관련 모든 변형 노드)는 고정 환경 요소로 완전 배제하고, AGV 차체 메쉬들만 `agvVehicleGroup`으로 분리 (Three.js 노드 이름 sanitize 대응)
- AGV 차량 바닥 타이어 접지면 최하단 Y값을 Y=0 평면에 접지되도록 영점 보정하여 바닥 평면 궤도 상에서 밀착 주행하도록 수정
- `Raycaster`를 통한 AGV 차량 메시 단독 클릭 이벤트 감지 및 마우스 호버 커서(`pointer`) 스타일링 적용
- 주행 진행 방향(`lookAt`)으로 차량 차체 그룹만 자연스럽게 회전 및 이동하도록 보간 연동

### 수정한 파일
- `viewer.html`
- `CHANGELOG.md`

### 테스트 및 검증 결과
- AGV 뷰어 페이지 접근 시 트랙 가이드라인 정상 렌더링 확인
- AGV 클릭 시 궤도 순환 주행 출발/일시정지 토글 기능 검증

## [2026-07-20] - 3D 예제 모델 5종 추가 및 대시보드 갱신

### 변경 목적
- `demo` 폴더 내 미등록되었던 5개의 GLB 입체 모델 예제(`agv`, `ballboy`, `lab`, `myroom`, `rose`)를 메인 대시보드 및 뷰어 시스템에 추가하여 총 8개 모델을 모두 탐색 가능하도록 확장.

### 주요 결정 사항
- `viewer.html` 내 `modelsConfig` 메타데이터 객체에 신규 5개 모델 경로 및 설명 정보 등록
- `index.html` 메인 대시보드 그리드에 신규 5개 모델 카드 링크 추가 (아이콘, 경로 태그, 상세 설명 적용)

### 수정한 파일
- `viewer.html`
- `index.html`
- `CHANGELOG.md`

### 테스트 및 검증 결과
- `index.html` HTML 구문 이상 없음 확인
- `viewer.html` 모델 키 매핑(`agv`, `ballboy`, `drill`, `lab`, `myroom`, `nc`, `robot`, `rose`) 정상 동작 검증
