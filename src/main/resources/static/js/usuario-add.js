$(document).ready(function () {
  $('input[name=telefones1]').mask('(00) 00000-0000');
  $('input[name=telefones2]').mask('(00) 00000-0000');
  $("#mensagem").hide();
})

$("#form-new-usuario").submit(function(evt){
	console.log("CADASTRO NOVO USUARIO");
	evt.preventDefault();

	var nome 		= $("#nome").val();
	var userName 	= $("#userName").val();
	var telefones1 	= $("#telefones1").val();
	var telefones2 	= $("#telefones2").val();
	var senha		= $("#senha").val();
	var email		= $("#email").val();

	var rolesUser = '';
	if($("#ROLE_ADMIN").is(":checked") == true){
		rolesUser  += "ROLE_ADMIN,";
	}
	if($("#ROLE_USUARIO").is(":checked") == true){
		rolesUser += "ROLE_USER,";
	}

	console.log("Nome Cliente: " + nome);
	console.log("Email: " + email);
	console.log("Nome Usuario: " + userName);
	console.log("Telefone 1: " + telefones1);
	console.log("Telefone 2: " + telefones2);
	console.log("Roler: " + rolesUser);

	$.ajax({
		method:"POST",
		url:"/usuario/salvar",
		data: {nome, email, userName, senha, telefones1, telefones2, rolesUser},
		beforeSend:function(){
			$("#submeter").addClass("spinner-border spinner-border-smr");
			$("#mensagem").removeClass("alert alert-success");
			$("#mensagem").removeClass("alert alert-danger");
			$("#mensagem").addClass("alert alert-warning").show();
			$("#mensagem").text("Atualizando, espero um momento");
			
			
		},
		success:function(result){

				$("#submeter").removeClass("spinner-border spinner-border-sm");	
				$("#mensagem").removeClass("alert alert-warning");
				$("#mensagem").addClass("alert alert-success");
				$("#mensagem").text("Usuario Cadastrado").show();
				document.getElementById("form-new-usuario").reset();
				console.log(result);
		},
		error:function(xhr){
			$("#submeter").removeClass("spinner-border spinner-border-sm");
			console.log("ERROR NOVO USUARIO");
			console.log("error > ", xhr.responseText);
			var errors = $.parseJSON(xhr.responseText);
			$.each(errors,function(key, val){
				console.log("KEY: "+ key + " - VAL: "+val);
				$("#"+ key).addClass("border border-danger");
			});
			$("#mensagem").addClass("alert alert-danger");
			$("#mensagem").show();
			$("#mensagem").text("Erro ao gravar novo usuario");
			alert("Erro ao gravar novo usuario");
			
		}
	})
})
