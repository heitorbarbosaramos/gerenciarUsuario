$(document).ready(function () {
  $('input[name=telefone]').mask('(00) 00000-0000');
  $("#mensagem").hide();
})
var listaTelefone = [];
var tel = '';
$("#addTelefone").on("click",function(){
	
	listaTelefone.push($("#telefone").val() + "<br/>");
  	document.getElementById("listaTelefone").innerHTML = listaTelefone ;

  	$("#telefone").val("");

	

	console.log(listaTelefone);
})



$("#form-new-usuario").submit(function(evt){
	console.log("CADASTRO NOVO USUARIO");
	evt.preventDefault();

	var idUsuario	= $("#idUsuario").val();
	var nome 		= $("#nome").val();
	var login 		= $("#login").val();
	var telefones 	= $("#listaTelefone").text();
	var senha		= $("#senha").val();
	var email		= $("#email").val();

	var rolesUser = '';
	if($("#ROLE_ADMIN").is(":checked") == true){
		rolesUser  += "ROLE_ADMIN,";
	}
	if($("#ROLE_USUARIO").is(":checked") == true){
		rolesUser += "ROLE_USUARIO,";
	}

	

	console.log("ID Cliente: " + idUsuario);
	console.log("Nome Cliente: " + nome);
	console.log("Email: " + email);
	console.log("Nome Usuario: " + login);
	console.log("Telefone : " + telefones);
	console.log("Roler: " + rolesUser);

	$.ajax({
		method:"POST",
		url:"/usuario/salvar",
		data: {idUsuario, nome, email, login, senha, telefones, rolesUser},
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
				$("#listaTelefone").text("");
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
