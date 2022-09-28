import React from "react"; 
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import '../../App.css';
import Cards from "../Cards";
import CardsProd from "./CardsProd";


export default function Products() {
    return(
        
    <div className="products">
        <h1>Click on item for bidding</h1>
        <Link to="/add-item">
        {sessionStorage.getItem("consumer")&&<Button variant="primary" size="lg" >Wanna Auction Your Product</Button>}
        </Link>
        <Cards/>
        <CardsProd/>
    </div> 
    
    );    
}