import React from "react";
import './Cards.css';
import CardItems from "./CardItems";


function Cards() {
  return (
    <div className="cards">
      <h1>Bid for these Ancient Antiques</h1>
      <div className="cards__container">
        <div className="cards__wrapper">
          <ul className="cards__items">
            <CardItems
              src="images/pot.jpg"
              text="Pot like Artifact"
              label="Antique"
              path="/bidding"
              value="7000"
            />
            <CardItems
              src="images/brassCandleStand.jpeg"
              text="Candle Stand from pre electricity era"
              label="Antique"
              path="/bidding"
              value="5000"
            />
          </ul>
          <ul className="cards__items">
            <CardItems
              src="images/gramophone.jpg"
              text="The old Music System"
              label="Antique"
              path="/bidding"
              value="12000"
            />
            <CardItems
              src="images/telephone2.jpg"
              text="A round dialer telephone"
              label="Antique"
              path="/bidding"
              value="10000"
            />
            <CardItems
            src="images/NauticalTelescope.jpg"
            text="A Handy Telescope"
            label="Antique"
            path="/bidding"
            value="5000"
            />
          </ul>
        </div>
      </div>
    </div>
  );
}
export default Cards;
