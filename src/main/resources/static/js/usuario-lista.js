$(document).ready(function () {
  $("#mensagem").hide();
})

$("button[id*='exluirUsuario-']").on("click", function(){
	var idUsuario = $(this).attr("id").split("-")[1];

	console.log("ESCLUIR USUARIO ID: " + idUsuario);

	$.ajax({
		method:"DELETE",
		url:"/usuario/excluir/"+idUsuario,
		beforeSend:function(){
			$(this).addClass("spinner-border spinner-border-smr");
			$("#mensagem").removeClass("alert alert-success");
			$("#mensagem").removeClass("alert alert-danger");
			$("#mensagem").addClass("alert alert-warning").show();
			$("#mensagem").text("Atualizando, espero um momento");	
		},
		success:function(result){

				$(this).removeClass("spinner-border spinner-border-sm");	
				$("#mensagem").removeClass("alert alert-warning");
				$("#mensagem").addClass("alert alert-success");
				$("#mensagem").text("Usuario excluido").show();
				location.reload();
				console.log(result);
		},
		error:function(xhr){
			$(this).removeClass("spinner-border spinner-border-sm");
			console.log("ERROR AO EXCLUIR USUARIO");
			console.log("error > ", xhr.responseText);
			var errors = $.parseJSON(xhr.responseText);
			$.each(errors,function(key, val){
				console.log("KEY: "+ key + " - VAL: "+val);
				$("#"+ key).addClass("border border-danger");
			});
			$("#mensagem").addClass("alert alert-danger");
			$("#mensagem").show();
			$("#mensagem").text("Erro ao gravar novo usuario");
			alert("Erro ao exluir usuario");
			
		}


	})

})