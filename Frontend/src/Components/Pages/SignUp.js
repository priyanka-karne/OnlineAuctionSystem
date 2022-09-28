import React from "react";
import "../../App.css";
import "./SignUp.css";
import { useRef, useState, useEffect } from "react";
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import axios from "../api/axios";

const PHONE_REGEX = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/;
const USER_REGEX = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = '/user/adduser';

const SignUp = () => {
  const [fname, setFname] = useState("");
  const [fnameFocus, setFnameFocus] = useState(false);

  const [lname, setLname] = useState("");
  const [lnameFocus, setLnameFocus] = useState(false);

  const [contact, setContact] = useState("");
  const [validContact, setValidContact] = useState(false);
  const [contactFocus, setContactFocus] = useState(false);

  const [addr, setAddr] = useState("");
  const [addrFocus, setAddrFocus] = useState(false);

  const userRef = useRef();
  const errRef = useRef();

  const [user, setUser] = useState("");
  const [validUserName, setValidUserName] = useState(false);
  const [userFocus, setUserFocus] = useState(false);

  const [pwd, setPwd] = useState("");
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);

  const [matchPwd, setMatchPwd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    setValidContact(PHONE_REGEX.test(contact));
  }, [contact]);

  useEffect(() => {
    setValidUserName(USER_REGEX.test(user));
  }, [user]);

  useEffect(() => {
    setValidPwd(PWD_REGEX.test(pwd));
    setValidMatch(pwd === matchPwd);
  }, [pwd, matchPwd]);

  useEffect(() => {
    setErrMsg("");
  }, [ user, pwd, matchPwd]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    // if button enabled with JS hack
    const v1 = USER_REGEX.test(user);
    const v2 = PWD_REGEX.test(pwd);
    if (!v1 || !v2) {
      setErrMsg("Invalid Entry");
      return;
    }
    try {
      const response = await axios.post(
        REGISTER_URL,
        { address:addr , contact:contact, email:user, firstName:fname,lastName:lname, password:pwd }
        // {
        //   headers: { "Content-Type": "application/json" },
        //   withCredentials: true
        // }
      );
      console.log(response?.data);
      console.log(response?.accessToken);
      console.log(JSON.stringify(response));
      setSuccess(true);
      
      //clear state and controlled inputs
      //need value attrib on inputs for this
      setFname("");
      setLname("");
      setContact("");
      setAddr("");
      setUser("");
      setPwd("");
      setMatchPwd("");
    } catch (err) {
      if (!err?.response) {
        setErrMsg("No Server Response");
      } else if (err.response?.status === 409) {
        setErrMsg("Username Taken");
      } else {
        setErrMsg("Registration Failed");
      }
      errRef.current.focus();
    }
  };

  return (
    <>
      {success ? (
        <section>
        <h1>You are registered successfully!</h1>
        <br />
        <p>
            <a href="/sign-in">Go to SignIn</a>
        </p>
    </section>
      ) : (
        <section>
          <p
            ref={errRef}
            className={errMsg ? "errmsg" : "offscreen"}
            aria-live="assertive"
          >
            {errMsg}
          </p>
          <h1>Register</h1>
          <form onSubmit={handleSubmit}>
            <label htmlFor="firstname">
              First Name:
              <FontAwesomeIcon icon={faCheck} className={fname ? "" : "hide"} />
            </label>
            <input
              type="text"
              id="firstname"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setFname(e.target.value)}
              value={fname}
              required
              onFocus={() => setFnameFocus(true)}
              onBlur={() => setFnameFocus(false)}
            />

            <label htmlFor="lastname">
              Last Name:
              <FontAwesomeIcon icon={faCheck} className={lname ? "" : "hide"} />
            </label>
            <input
              type="text"
              id="lastname"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setLname(e.target.value)}
              value={lname}
              required
              onFocus={() => setLnameFocus(true)}
              onBlur={() => setLnameFocus(false)}
            />

            <label htmlFor="contact">
              Contact Number:
              <FontAwesomeIcon
                icon={faCheck}
                className={validContact ? "valid" : "hide"}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validContact || !contact ? "hide" : "invalid"}
              />
            </label>
            <input
              type="text"
              id="contact"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setContact(e.target.value)}
              value={contact}
              required
              onFocus={() => setContactFocus(true)}
              onBlur={() => setContactFocus(false)}
            />
            <p
              id="uidcont"
              className={
                contactFocus && contact && !validContact
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Enter Country Code & Contact Number
              <br />
              Enter digits only
              <br />+ , - , ( ) are allowed.
            </p>

            <label htmlFor="addr">
              Address:
              <FontAwesomeIcon icon={faCheck} className={addr ? "" : "hide"} />
            </label>
            <input
              type="textarea"
              id="addr"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setAddr(e.target.value)}
              value={addr}
              required
              onFocus={() => setAddrFocus(true)}
              onBlur={() => setAddrFocus(false)}
            />

            <label htmlFor="username">
              Username:
              <FontAwesomeIcon
                icon={faCheck}
                className={validUserName ? "valid" : "hide"}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validUserName || !user ? "hide" : "invalid"}
              />
            </label>
            <input
              type="text"
              id="username"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setUser(e.target.value)}
              value={user}
              required
              aria-invalid={validUserName ? "false" : "true"}
              aria-describedby="uidnote"
              onFocus={() => setUserFocus(true)}
              onBlur={() => setUserFocus(false)}
            />
            <p
              id="uidnote"
              className={
                userFocus && user && !validUserName
                  ? "instructions"
                  : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Enter valid email
            </p>

            <label htmlFor="password">
              Password:
              <FontAwesomeIcon
                icon={faCheck}
                className={validPwd ? "valid" : "hide"}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validPwd || !pwd ? "hide" : "invalid"}
              />
            </label>
            <input
              type="password"
              id="password"
              onChange={(e) => setPwd(e.target.value)}
              value={pwd}
              required
              aria-invalid={validPwd ? "false" : "true"}
              aria-describedby="pwdnote"
              onFocus={() => setPwdFocus(true)}
              onBlur={() => setPwdFocus(false)}
            />
            <p
              id="pwdnote"
              className={pwdFocus && !validPwd ? "instructions" : "offscreen"}
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              8 to 24 characters.
              <br />
              Must include uppercase and lowercase letters, a number and a
              special character.
              <br />
              Allowed special characters:{" "}
              <span aria-label="exclamation mark">!</span>{" "}
              <span aria-label="at symbol">@</span>{" "}
              <span aria-label="hashtag">#</span>{" "}
              <span aria-label="dollar sign">$</span>{" "}
              <span aria-label="percent">%</span>
            </p>

            <label htmlFor="confirm_pwd">
              Confirm Password:
              <FontAwesomeIcon
                icon={faCheck}
                className={validMatch && matchPwd ? "valid" : "hide"}
              />
              <FontAwesomeIcon
                icon={faTimes}
                className={validMatch || !matchPwd ? "hide" : "invalid"}
              />
            </label>
            <input
              type="password"
              id="confirm_pwd"
              onChange={(e) => setMatchPwd(e.target.value)}
              value={matchPwd}
              required
              aria-invalid={validMatch ? "false" : "true"}
              aria-describedby="confirmnote"
              onFocus={() => setMatchFocus(true)}
              onBlur={() => setMatchFocus(false)}
            />
            <p
              id="confirmnote"
              className={
                matchFocus && !validMatch ? "instructions" : "offscreen"
              }
            >
              <FontAwesomeIcon icon={faInfoCircle} />
              Must match the first password input field.
            </p>

            <button
              disabled={
                !validUserName || !validPwd || !validMatch ? true : false
              }
            >
              Sign Up
            </button>
          </form>
          <p>
            Already registered?
            <br />
            <span className="line">
              {/*put router link here*/}
              <a href="/sign-in">Sign In</a>
            </span>
          </p>
        </section>
      )}
    </>
  );
};

export default SignUp;
