import React,{useState,useEffect} from 'react'
import { form } from "react-bootstrap";
import axios from 'axios';

export default function Signup() {

    const [username, setUsername] = useState("");
    const [password, setPassword] =useState("");
    const [tokenusername,setTokenusername] = useState("");
    const [tokenpassword,setTokenpassword] = useState ("");
    const [jwt,setJwt] = useState ("");

    const handleSignUp = (e) =>{
        e.preventDefault()
        axios.post('http://localhost:8080/sign-up',{
            username:username,
            password:password})
    };

    const handleTokenRequest=(e)=>{
        e.preventDefault()
        axios.post('http://localhost:8080/token',{
            username:tokenusername,
            password:tokenpassword
        })
    };

    const headers = {
        'Authorization':'Bearer '+ jwt
    }
    const invokeUser=(e)=>{
        e.preventDefault()
        axios.get('http://localhost:8080/user',{headers:headers})
    }

    return (
        <div>
            <div class="container">
                <div class="card" >
                    <div class="card-body">
                        <h1>User Signup</h1>
                        <form onSubmit={handleSignUp}>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Username</label>
                                <input type="text" class="form-control" value={username} onChange={e => setUsername(e.target.value)} id="username" aria-describedby="emailHelp" placeholder="Enter Username" required/>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label>
                                <input type="password" class="form-control" id="password" value={password} onChange={e => setPassword(e.target.value)} placeholder="Enter Password" required/>
                            </div>
                            <button type="submit" class="btn btn-primary">SignUP</button>
                        </form>
                    </div>
                </div>
            </div>
            <br/>
            <div class="container">
                <div class="card" >
                    <div class="card-body">
                        <h1>Request JWT Token</h1>
                        <form onSubmit={handleTokenRequest}>
                            <div class="form-group">
                                <label for="TokenUsername">Username</label>
                                <input type="text" class="form-control" value={tokenusername} onChange={e => setTokenusername(e.target.value)} id="tokenusername" aria-describedby="emailHelp" placeholder="Enter Username" required/>
                            </div>
                            <div class="form-group">
                                <label for="TokenPassword">Password</label>
                                <input type="password" class="form-control" id="tokenpassword" value={tokenpassword} onChange={e => setTokenpassword(e.target.value)} placeholder="Enter Password" required/>
                            </div>
                            <button type="submit" class="btn btn-primary">Request Token</button>
                        </form>
                    </div>
                </div>
            </div>
            <br/>
            <div class="container">
                <div class="card" >
                    <div class="card-body">
                        <h1>Invoke User Endpoint</h1>
                        <form onSubmit={invokeUser}>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Input JWT Token</label>
                                <input type="text" class="form-control" value={jwt} onChange={e => setJwt(e.target.value)} id="jwt" aria-describedby="emailHelp" placeholder=" JWT " required/>
                            </div>
                            <button type="submit" class="btn btn-primary">Invoke User Endpoint</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}
