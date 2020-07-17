import React, { useState } from 'react'
import { form } from "react-bootstrap";
import axios from 'axios';
import { ToastMessage } from './ToastMessage';

export default function Signup() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [tokenusername, setTokenusername] = useState("");
    const [tokenpassword, setTokenpassword] = useState("");
    const [jwt, setJwt] = useState("");
    const [jwtvalue, setJwtvalue] = useState("");
    const [userresponse, setUserresponse] = useState("");
    const [show, setShow] = useState(false);
    const [signupResponse, setSignupresponse] = useState("");

    const handleSignUp = (e) => {
        e.preventDefault()
        axios.post('http://localhost:8080/sign-up', {
            username: username,
            password: password
        })
            .then((response) => {
                if (response.data != null) {
                    setShow(true);
                    setSignupresponse(response.data)
                }
            }, (err) => {
                console.log(err);
            })
    };

    const handleTokenRequest = (e) => {
        e.preventDefault()
        axios.post('http://localhost:8080/token', {
            username: tokenusername,
            password: tokenpassword
        })
            .then((response) => {
                setJwtvalue(response.data.jwt)
                console.log(response.data.jwt);
            }, (error) => {
                setJwtvalue(error);
                console.log(error);
            });
    };

    const headers = {
        'Authorization': 'Bearer ' + jwt
    }
    const invokeUser = (e) => {
        e.preventDefault()
        axios.get('http://localhost:8080/user', { headers: headers })
            .then((response) => {
                setUserresponse(response.data);
                console.log(response.data);
            }, (error) => {
                setUserresponse(error);
                console.log(error);
            })
    }

    const invokeHello = (e) => {
        e.preventDefault()
        axios.get('http://localhost:8080/hello', { headers: headers })
            .then((response) => {
                setUserresponse(response.data);
                console.log(response.data);
            }, (error) => {
                console.log(error);
            })
    }

    return (
        <div>
            <div style={{ "display": show ? "block" : "none" }}>
                <ToastMessage data={{ show: show, message: signupResponse }} />
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card" >
                            <div class="card-body">
                                <h1>User Signup</h1>
                                <form onSubmit={handleSignUp}>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">Username</label>
                                        <input type="text" class="form-control" value={username} onChange={e => setUsername(e.target.value)} id="username" aria-describedby="emailHelp" placeholder="Enter Username" required />
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputPassword1">Password</label>
                                        <input type="password" class="form-control" id="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Enter Password" required />
                                    </div>
                                    <button type="submit" class="btn btn-primary">SignUP</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card" >
                            <div class="card-body">
                                <h1>Request A JWT Token</h1>
                                <form onSubmit={handleTokenRequest}>
                                    <div class="form-group">
                                        <label for="TokenUsername">Username</label>
                                        <input type="text" class="form-control" value={tokenusername} onChange={e => setTokenusername(e.target.value)} id="tokenusername" aria-describedby="emailHelp" placeholder="Enter Username" required />
                                    </div>
                                    <div class="form-group">
                                        <label for="TokenPassword">Password</label>
                                        <input type="password" class="form-control" id="tokenpassword" value={tokenpassword} onChange={e => setTokenpassword(e.target.value)} placeholder="Enter Password" required />
                                    </div>
                                    <button type="submit" class="btn btn-primary">Request Token</button>
                                </form>
                                <div class="form-group">
                                    <br />
                                    <input type="text" class="form-control" id="jwt" value={jwtvalue} placeholder="Requested JWT" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br />

            <div class="container">
                <div class="card" >
                    <div class="card-body">
                        <h1>Invoke User/Hello Endpoint</h1>
                        <form >
                            <div class="form-group">
                                <label for="exampleInputEmail1">Input JWT Token</label>
                                <input type="text" class="form-control" value={jwt} onChange={e => setJwt(e.target.value)} id="jwt" aria-describedby="emailHelp" placeholder=" JWT " required />
                            </div>
                            <div class="container">
                                <div>
                                    <button type="submit" class="btn btn-primary" onClick={invokeUser}>Invoke User Endpoint</button>
                                    <button type="submit" class="btn btn-primary ml-3" onClick={invokeHello}>Invoke Hello Endpoint</button>
                                </div>
                            </div>
                        </form>
                        <div class="form-group">
                            <br />
                            <input type="text" class="form-control" id="userresponse" value={userresponse} placeholder=" Response " />
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}
