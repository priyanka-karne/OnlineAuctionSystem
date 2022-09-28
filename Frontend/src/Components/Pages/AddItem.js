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

const logId = JSON.stringify(sessionStorage.getItem("consumer").id);

const AddItem_URL = '/product/addproduct';

const AddItem = () => {
  const [amount, setAmount] = useState("");
  const [amountFocus, setamountFocus] = useState(false);

  const [details, setDetails] = useState("");
  const [detailsFocus, setDetailsFocus] = useState(false);

  const [imageURL, setImageURL] = useState("");
  const [imageURLFocus, setImageURLFocus] = useState(false);

  const itemRef = useRef();
  const errRef = useRef();


  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

//   useEffect(() => {
//     amountFocus.current.focus();
//   }, []);

  useEffect(() => {
    setErrMsg("");
  }, [ imageURL , amount]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    // if button enabled with JS hack
    try {
      const response = await axios.post(
        AddItem_URL,
        { baseAmount:amount , details:details, imageURL:imageURL }
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
      setAmount("");
      setDetails("");
      setImageURL("");
    } catch (err) {
      if (!err?.response) {
        setErrMsg("No Server Response");
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
        <h1>Your item added successfully!</h1>
        <br />
        <p>
            <a href="/products">Go to products</a>
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
          <h1>Add Item</h1>
          <form onSubmit={handleSubmit}>
            <label htmlFor="amount">
              Base Amount
              <FontAwesomeIcon icon={faCheck} className={amount? "" : "hide"} />
            </label>
            <input
              type="number"
              id="amount"
              ref={itemRef}
              autoComplete="off"
              onChange={(e) => setAmount(e.target.value)}
              value={amount}
              required
              onFocus={() => setamountFocus(true)}
              onBlur={() => setamountFocus(false)}
            />

            <label htmlFor="details">
              Details
              <FontAwesomeIcon icon={faCheck} className={details? "" : "hide"} />
            </label>
            <input
              type="text"
              id="details"
              ref={itemRef}
              autoComplete="off"
              onChange={(e) => setDetails(e.target.value)}
              value={details}
              required
              onFocus={() => setDetailsFocus(true)}
              onBlur={() => setDetailsFocus(false)}
            />

            <label htmlFor="imageURL">
            Image URL:
            <FontAwesomeIcon icon={faCheck} className={imageURL? "" : "hide"} />
            </label>
            <input
              type="url"
              id="imageURL"
              ref={itemRef}
              autoComplete="off"
              onChange={(e) => setImageURL(e.target.value)}
              value={imageURL}
              required
              onFocus={() => setImageURLFocus(true)}
              onBlur={() => setImageURLFocus(false)}
            />
            <button>
              Enter
            </button>
          </form>
        </section>
      )}
    </>
  );
};

export default AddItem;
