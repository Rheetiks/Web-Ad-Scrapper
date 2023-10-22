const express = require("express");
const puppeteer = require('puppeteer');

const app = express ();
const cors = require('cors'); 

app.use(express.json());
const PORT = process.env.PORT || 3010;

app.use(cors({
    origin: 'http://localhost:3000'
}));


app.listen(PORT, () => {
    console.log("Server Listening on PORT:", PORT);
  });
  
 


app.use(express.text());
app.post("/getProduct", async (request, response) => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    const query=request.body;
    out={};
    try {
        
        await page.goto(`https://www.google.com/search?q=${encodeURIComponent(query)}`);
        await page.waitForSelector('#bGmlqc', { timeout: 1000 });
        
        const ad = await page.$('#bGmlqc ');

        if (ad) {
                const productName = await ad.$eval(' .mnr-c .pla-unit-container .orXoSd div div', (span) => {
                    return span.innerText
                });

                const productImgUrl = await ad.$eval(' .mnr-c .pla-unit-container .Ter3Ue .pla-unit-img-container-link div div img', (img) =>{ 
                    return img.getAttribute('src')
                });

                const productPrice=await ad.$eval(' .mnr-c .pla-unit-container .orXoSd div .T4OwTb span',(span)=>{
                    return span.innerText;
                })
        
                out= {
                    productName,
                    productImgUrl,
                    productPrice,
                };
            response.send(out);
            
        }
    
    } catch (error) {
      response.send(null);
    } 
  
      return null;
    
 });
