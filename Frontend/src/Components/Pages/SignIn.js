import React from "react"; 
import '../../App.css';
import { useRef, useState, useEffect, useContext } from 'react';
import AuthContext from "../context/AuthProvider";

import axios from '../api/axios';
import { Redirect } from "react-router-dom";

const   LOGIN_URL = '/user/login';

const Signin = () => {
    const { setAuth } = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [user, pwd])

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(LOGIN_URL, {email:user, password:pwd });
            
            sessionStorage.setItem("consumer",JSON.stringify(response.data));

            console.log(JSON.stringify(response?.data));
            //console.log(JSON.stringify(response));
            const sessionId = response?.data?.session_id;
            
            setAuth({ user, pwd, });
            setUser('');
            setPwd('');
            setSuccess(true);       
            

        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            } else if (err.response?.status === 400) {
                    setErrMsg('Missing Username or Password');
            } else if (err.response?.status === 401) {
                setErrMsg('Unauthorized');
            } else {
                setErrMsg('Login Failed');
                console.log(err.response?.status)
            }
            errRef.current.focus();
        }
    }

    return (
        <>
            {success ? (
                // <section>
                //     <h1>You are logged in!</h1>
                //     <br />
                //     <p>
                //         <a href="/">Go to Home</a>
                //     </p>
                // </section>
                window.location="/products"
            ) : (
                <section>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Login</h1>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="username">Username:</label>
                        <input
                            type="text"
                            id="username"
                            ref={userRef}
                            autoComplete="off"
                            onChange={(e) => setUser(e.target.value)}
                            value={user}
                            required
                        />

                        <label htmlFor="password">Password:</label>
                        <input
                            type="password"
                            id="password"
                            onChange={(e) => setPwd(e.target.value)}
                            value={pwd}
                            required
                        />
                        <button>Sign In</button>
                    </form>
                    <p>
                        Need an Account?<br />
                        <span className="line">
                            {/*put router link here*/}
                            <a href="/sign-up">Sign Up</a>
                        </span>
                    </p>
                </section> 
            )
            }</>
    )
}

export default Signin