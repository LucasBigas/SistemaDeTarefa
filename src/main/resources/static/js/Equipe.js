(function(){
    $(".cards").on("click",".delete-btn",function(){
        let botaoClicado =$(this);
        $("#btnsim").attr("data-id",botaoClicado.attr("data-id"));
        $("#modalequipe").modal("show");
    });

    $("#btnsim").on("click",function(){
        let botaoSim =$(this);
        let id =botaoSim.attr("data-id");
        $.ajax({
            url: "/equipe/deletar/" + id,
            method: "GET",
            success:function(){
                window.location.href="/equipe";
            }
        });
    });

})();