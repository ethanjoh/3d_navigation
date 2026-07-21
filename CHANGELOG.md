<!-- Three.js GLB 뷰어 프로젝트의 변경 사항과 의사 결정 내역을 기록하는 문서 -->
# CHANGELOG

## [2026-07-21]

### Added
- `demo` 디렉터리에 추가된 WRL 3D 모델 파일 8종(`agv.wrl`, `ballboy.wrl`, `drill.wrl`, `lab.wrl`, `myroom.wrl`, `nc.wrl`, `robot.wrl`, `rose.wrl`) 버전 관리 추적 및 동기화

## [2026-07-15]

### Added
- 메인 포털 화면 `index.html` 구현 (글래스모피즘 테마 및 세련된 카드 레이아웃 적용)
- Three.js 통합 뷰어 화면 `viewer.html` 구현 (비동기 GLB 모델 로딩, 로딩 진행률 표시기 탑재)
- 공통 프리미엄 다크 테마 스타일시트 `style.css` 구현

### Changed
- WebGLRenderer 초기화 시 HTML에 명시적으로 정의된 `<canvas>` 요소를 재사용하도록 개선하여 브라우저의 WebGL 컨텍스트 재생성 오류 방지 및 메모리 사용 안정성 향상
- 모델 로딩 시 바운딩 박스를 기준하여 카메라의 줌과 중심점을 기하학적으로 자동 조절하도록 기능 추가
- 깃허브 페이지 배포 호환을 위해 뷰어 앱 관련 리소스 및 3D GLB 모델 파일 일체를 `demo` 폴더 구조로 패키징 및 복사 이전 완료
- `index.html` 메인 헤더 영역에 WRL ➔ Blender GLB 변환 방법 및 무설치 감상 안내 문구 추가 (src 및 demo)
- `demo` 폴더만 GitHub Pages에 독립적으로 자동 배포해 주는 GitHub Actions 워크플로우(`.github/workflows/deploy.yml`) 설정 추가
- `demo` 폴더에 로컬 실행 가이드와 조작 안내를 서술한 `README.md` 문서 추가
- 메인 대시보드(`index.html`) 우측 상단에 깃허브 저장소(`3d_navigation`)로 이동하는 Glassmorphism 스타일 복귀 버튼 및 관련 CSS/반응형 미디어 쿼리 추가 (src 및 demo)

