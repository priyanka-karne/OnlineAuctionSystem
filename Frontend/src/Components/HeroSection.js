import React from 'react'
import { Button } from './Button'
import '../App.css'
import './HeroSection.css'

function HeroSection() {
  return (
    <div className='hero-container'>
        <video src='videos/home.mp4' autoPlay loop muted/>
        <h1>Come on Collectors</h1>
        <p>Bid now, what are you waiting for?</p>
    </div>
  )
}

export default HeroSection