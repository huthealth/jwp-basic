// $(".qna-comment").on("click", ".answerWrite input[type=submit]", addAnswer);
$(".answerWrite input[type=submit]").click(addAnswer);
$(".qna-comment-slipp-articles button[type=submit]").click(deleteAnswer); // form-delete는?


function deleteAnswer(e) {
  e.preventDefault();

  var deleteButton = $(this);
  var queryString = deleteButton.closest("form").serialize();
  //queryJson.add($("input[name=questionId]"));

  $.ajax({
    type : 'post',
    url : '/api/qna/deleteAnswer',
    data : queryString,
    dataType: 'json',
    error : onError,
    success :function (json,status) {
      if(json.result.status) {
        var countOfComment = json.countOfComment;
        deleteButton.closest(".article").remove();
        $(".qna-comment-count").text(countOfComment + "개의 의견");
      }
    },
  });
}

function addAnswer(e) {
  e.preventDefault();

  var queryString = $("form[name=answer]").serialize();

  $.ajax({
    type : 'post',
    url : '/api/qna/addAnswer',
    data : queryString,
    dataType : 'json',
    error: onError,
    success : onSuccess,
  });
}

function onSuccess(json, status){
  var answer = json.answer;
  var countOfComment = json.countOfComment;
  var answerTemplate = $("#answerTemplate").html();
  var template = answerTemplate.format(answer.writer, new Date(answer.createdDate), answer.contents, answer.answerId);
  $(".qna-comment-slipp-articles").prepend(template);
  $(".qna-comment-count").text(countOfComment + "개의 의견");
}

function onSuccess2(json, status) {
  var result = json.result.status;
  alert(result);
  alert( $(this).closest(".article").textContent);

  if(result.toString() === "true"){
    $(this).closest(".article").remove();
    alert("success");
  }
  else{
    alert("delete answer failed");
  }
}

function onError(xhr, status) {
  alert("error");
}

String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};