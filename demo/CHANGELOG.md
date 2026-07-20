<!-- 변경 이력을 관리하는 CHANGELOG 문서 -->
# Changelog

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
