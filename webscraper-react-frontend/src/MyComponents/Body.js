
import React, { useState } from 'react';

import { Product } from './Product';

import { NoProduct } from './NoProduct';

export const Body = (props) => {
  const[query,setQuery]=useState("");

    function displayPostApi(input, url) {
      let result;
      async function callApi() {
          result = await fetch(url, {
              method: 'POST',
              headers: {
                'Content-Type': 'text/plain',
              },
              body: input
          });

          const contentType = result.headers.get('Content-Type');
          if (contentType && contentType.includes('application/json')) {
              return result.json();
          }
          return result.text();
      }
      return callApi();
    }

  function displayGetApi(url) {
    let result;
      async function callApi() {
          result = await fetch(url);
          const contentType = result.headers.get('Content-Type');
          if (contentType && contentType.includes('application/json')) {
              return result.json();
          }
          return result.text();
      }
      return callApi();
  }

  const[product,setproduct]=useState(false);
  const[productDetails,setProductDetails]=useState("");

  const[showComponent,setShowComponent]=useState(false);
  const getProduct =()=>{
    console.log("hi");
    document.getElementById('loader-container1').style.display="flex";
    displayPostApi(query, 'http://localhost:3010/getProduct').then((val) => {
          console.log(val);
          if (typeof val === 'object') {
            setProductDetails(val);
            setproduct(true);
          } else {
            setProductDetails("");
            setproduct(false);
          }
          document.getElementById('loader-container1').style.display="none";
        });
        

  }

  const handleClick=(e)=>{
    setQuery(e.target.value)
  }

  const speakText =()=>{
    const speech = window.speechSynthesis;
      
    if(productDetails){
      const Text = new SpeechSynthesisUtterance('The price of the product '+  productDetails.productName +'is '  +productDetails.productPrice);
      speech.speak(Text);
    }else{
      const Text = new SpeechSynthesisUtterance('Please search Something');
      speech.speak(Text);
    }
  }



  

  return (
    <>
    <div className='main'>
      <div id="loader-container1" className='loader-container'>
        <div className='loader'></div>
      </div>

      <div className="container">
        

          <h1>Ad Scraper</h1>
          <p>Enter your search query to find the first ad:</p>
          <input type="text" value={query} onChange={handleClick}  id="search-box" placeholder="Enter your query" />
          <div id="button-container">
            <button id="search-button" onClick={getProduct} >Search</button>
            <i className="bi bi-volume-up" onClick={speakText} id="speak-btn" color='black'></i>
          </div>
          
          {!product && <NoProduct />}
          {product && <Product productDetails={productDetails}/>}
          
      </div>
    </div>
    </>
  )
}
