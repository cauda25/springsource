// 이미지 모달 요소 가져오기
const imgModal = document.querySelector("#imgModal");

if (imgModal) {
  imgModal.addEventListener("show.bs.modal", (e) => {
    // 모달을 뜨게 만들 li 요서 가져오기
    const posterLi = e.relatedTarget;

    //data- 가져오기
    const file = posterLi.getAttribute("data-file");
    console.log(file);

    imgModal.querySelector(".modal-title").textContent = `${title}`;
    imgModal.querySelector(
      ".modal-body"
    ).innerHTML = `<img src="/upload/display?fileName=${file}&size=1" alt="" style="width:100%"/>`;
  });
}
