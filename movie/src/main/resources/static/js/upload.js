// [type=file] change event 처리
const fileImput = document.querySelector("[type='file']");

function showUploadImage(files) {
  const output = document.querySelector(".uploadResult ul");
  let tags = "";
  files.forEach((file) => {
    tags += `<li data-name="${file.fileName}" data-path="${file.folderPath}" data-uuid="${file.uuid}"><div>`;
    tags += `<a href=""><img src="/upload/display?fileName=${file.thumbImageURL}" alt="" class="block" /></a>`;
    tags += `<span class="text-sm d-inline-block mx-1">${file.fileName}</span>`;
    tags += `<a href="${file.imageURL}" data-file=""><i class="fa-solid fa-xmark"></i></a>`;
    tags += `</div></li> `;
  });
  output.insertAdjacentHTML("beforeend", tags);
}

fileImput.addEventListener("change", (e) => {
  const files = e.target.files;

  let formDate = new FormData();
  for (let index = 0; index < files.length; index++) {
    formDate.append("uploadFiles", files[index]);
  }
  fetch("/upload/upload", {
    method: "post",
    body: formDate,
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // 첨부 파일 화면 출력
      showUploadImage(data);
    });
});

// 작성 버튼 클릭 시
// form submit 중지
document.querySelector("#createForm").addEventListener("submit", (e) => {
  e.preventDefault();

  // 첨부파일 정보 수집
  // 요소.dataset.name
  const attachInfors = document.querySelectorAll(".uploadResult li");
  let result = "";
  attachInfors.forEach((obj, idx) => {
    // console.log(idx);
    // console.log(obj.dataset.name);
    // console.log(obj.dataset.path);
    // console.log(obj.dataset.uuid);
    result += `<input type="hidden" name="movieImageDTOs[${idx}].path" value="${obj.dataset.path}"> `;
    result += `<input type="hidden" name="movieImageDTOs[${idx}].uuid" value="${obj.dataset.uuid}"> `;
    result += `<input type="hidden" name="movieImageDTOs[${idx}].imgName" value="${obj.dataset.name}"> `;
  });
  e.target.insertAdjacentHTML("beforeend", result);

  // 폼 내용 확인
  console.log(e.target.innerHTML);

  e.target.action = "/movie/create";
  e.target.submit();
});

document.querySelector(".uploadResult ul").addEventListener("click", (e) => {
  e.preventDefault();

  const element = e.target.closest("a");

  const removeLi = e.target.closest("li");

  const filePath = element.getAttribute("href");

  let formDate = new FormData();
  formDate.append("filePath", filePath);
  fetch("/upload/remove", {
    method: "post",
    body: formDate,
  })
    .then((response) => {
      if (!response.ok) throw new Error("에러발생");
      return response.text();
    })
    .then((data) => {
      // 화면 이미지 제거
      if (data) removeLi.remove();
    });
});
