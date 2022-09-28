import React, { useEffect, useState } from "react";
import '../Cards.css';
import CardItems from "../CardItems";
import axios from "axios";
import { useHistory } from "react-router-dom";

function CardsProd() {

  const [product, setProduct] = useState([]);
  // const history = useHistory()
  const getAllProduct = () => {
    axios.get("http://localhost:8080/product").then(res => {
      console.log(res)
      // history.push("/")
      setProduct(res.data.data);
    }).catch(err => { console.log(err) })
  }

  useEffect(() => {
    getAllProduct()

  }, [])


  return (

    <>
      {

        product.map(p => (
          <div className="cards">
            <h1>Bid for these Ancient Antiques</h1>
            <div className="cards__container">
              <ul className="cards__items">
                <CardItems
                  product={p.data}
                />

              </ul>
            </div>
          </div>
        )
        )

      }
    </>
  )

}
export default CardsProd;
