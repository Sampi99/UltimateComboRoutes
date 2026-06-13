import React from "react";
import "./App.css";
import { ShowSmashCharacters } from "../../smashCharacters";

const Home = () => {

  return (
    <div className="App">
	    <p> ¡Bienvenidos a Ultimate Combo Routes! </p>
      <ShowSmashCharacters/>

    </div>
  );
};

export default Home;