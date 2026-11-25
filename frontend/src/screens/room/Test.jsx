import React, {userState, userRef} from "react";
import axios from "axios";

function Test(){

    const test = () =>{
        axios({
            method : "GET",
            url : `http://deployment.example.invalid:8080/auth-service/api/test`
        })
            .then((res) =>{
                concole.log("API Test");
                console.log(res);
            })
    }
    return(
        <button type="button" onClick={test}>API Test</button>
    );
}

export default Test;