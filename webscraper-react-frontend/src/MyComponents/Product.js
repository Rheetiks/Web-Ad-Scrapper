import React from 'react'

export const Product = ({productDetails}) => {
  return (
    <>
    <div className='product-container'>
  
      <div className="product-card">
              <img className="product-image" src={productDetails.productImgUrl} alt="Product" />
              <div className="product-details">
                  <h4>{productDetails.productName}</h4>
                  <p>{productDetails.productPrice}</p>
              </div>  
              
      </div>
      <div className='product-info'>
          <p>The price of the product {productDetails.productName} is {productDetails.productPrice}</p>
      </div>

    </div>
    </>
  )
}
