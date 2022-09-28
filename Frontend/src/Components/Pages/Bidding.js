import React from "react";
import '../../App.css';
import './SignUp.css';
import { useRef, useState, useEffect, useContext } from 'react';
import AuthContext from "../context/AuthProvider";




const Bidding = () => {
    const { setAuth } = useContext(AuthContext);
    const bidRef = useRef();
    const errRef = useRef();

    const [name, setName] = useState('');
    const [amount, setAmount] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        bidRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [name, amount])

    const handleSubmit = async(e) => {
        setSuccess(true)
    };

    return (
        <>
            {success ? (
                <section>
                    <h1>Congratilatoions {name}</h1>
                    <br />
                    <h3>You just bid for this Item for Rs {amount}</h3>
                    <br />
                    <p>
                        <a href="/products">Go to Products</a>
                    </p>
                </section>
            ) : (
                <section>
                    <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"} aria-live="assertive">{errMsg}</p>
                    <h1>Bidding</h1>
                    <form onSubmit={handleSubmit}>
                        <label htmlFor="Name">Name:</label>
                        <input
                            type="text"
                            id="name"
                            ref={bidRef}
                            autoComplete="off"
                            onChange={(e) => setName(e.target.value)}
                            value={name}
                            required
                        />

                        <label htmlFor="amount">Amount:</label>
                        <input
                            type="number"
                            id="amount"
                            onChange={(e) => setAmount(e.target.value)}
                            value={amount}
                            required
                        />
                        <button>Submit</button>
                    </form>
                </section>
            )
            }
        </>

    )
}
export default Bidding