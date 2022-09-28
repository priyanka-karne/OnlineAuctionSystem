import React from 'react'
import { Link } from 'react-router-dom'
import "./Cards.css"

function CardItems(props) {
    return (
        <>  
            <h1>{props.id}</h1>
            {
                // <div className="cards">
                //     <h1>Bid for these Ancient Antiques</h1>
                //     <div className="cards__container">
                //         <ul className="cards__items">
                            <li className='cards__item'>
                                <Link className='cards__item__link' to='/bidding'>
                                    <figure className='cards__item__pic-wrap' data-category={props.label}>
                                        <img src={props.src} alt='Antique Image' className='cards__item__img' />
                                    </figure>
                                    <div className='cards__item__info'>
                                        <h5 className='cards__item__text'>{props.text}</h5>
                                        <h3 className='cards__item__text'>Rs. {props.value}</h3>
                                    </div>
                                </Link>
                            </li>
                //         </ul>
                //     </div>
                // </div>
            }
        </>
    )
}

export default CardItems;