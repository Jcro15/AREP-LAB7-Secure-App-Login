var api = (function () {
    var url="https://ec2-54-157-184-55.compute-1.amazonaws.com:8080";
    //var url="https://localhost:5000"

    function fillPage(){
        axios.get(url+"/protected/user").then(res=>{
            $("#username").text(res.data);
        })
        axios.get(url+"/protected/callservice").then(res=>{
            $("#serviceResponse").text(res.data);
        })
    }
    function login(){
        var user={email:document.getElementById("email").value,password:document.getElementById("password").value}
        axios.post(url+"/login",user).then(res=>{
            if(res.data!=""){
                alert(res.data)
            }
            else {
                window.location.href="protected/index.html";
            }

        })
    }



    return {
        login:login,
        fillPage:fillPage
    };
})();