// we use ethers.js which is a library for interacting with the Ethereum Blockchain and its ecosystem
// also we use express js to work with 
const ethers = require('ethers');
const express = require('express');

console.log('hello world');

const app = express();

// here we define port from heroku host enviroments or 9002 as default port
const port = process.env.PORT || 9002;

// we save the contract address that refer to our smart contract we deployed
const CONTRACT_ADDRESS = "0x6ad6eFD1b65463c4CAb116B5f6a3Abe5CE0c3244"; 

// we write the function header with all its params, visibility and return type
// note: without body 

const contracte = require("./now.json");

  // this is the account address that we will send tokens for
  const recieved_account =  "0x18a4f886Ef02EeBA8bcC62aE2DEDBE66A51d7010"; 

  // this private key for a wallet that we will take the fees from
  const privateKey = "0bd364665631d506dab6f0eabdae3718a1313d89b67a6fba9dc1855e554ea0f1"; 

// we initialize a post,get api and pass values which will be the argument for our contract method
app.post('/newUser', async (req, res) => {
 try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');
  


  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi and pass it the known variables
  const result = await contract.newUser(recieved_account);
  
  // just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.post('/newTask', async (req, res) => {
 try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');
  
  // get data from path
  const title = req.query.title;
  const description = req.query.description;
  const reward = req.query.reward; 

  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi and pass it the known variables
  const result = await contract.createTask(title, description, reward);
    
// just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.post('/toggleDone', async (req, res) => {
 
  try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');
  
  // get data from path
  const id = req.query.taskId;

  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi and pass it the known variables
  const result = await contract.toggleDone(id);
    
  // just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.post('/toggleFinesh', async (req, res) => {
 
 try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');
  
  // get data from path
  const id = req.query.taskId;

  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi and pass it the known variables
  const result = await contract.toggleFinesh(id);
    
  // just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.get('/getTasksUnComplete', async (req, res) => {
  try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');
  

  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi
  const result = await contract.getTasksUnComplete();
    
  // just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.get('/getTasksUnFinesh', async (req, res) => {
   try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');

  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi
   const result = await contract.getTasksUnFinesh();
    
  // just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.get('/getMyActions', async (req, res) => {
   try{
	 
// we use ethers.getDefaultProvider to pass the ether test network provider 
 const provider = ethers.getDefaultProvider('ropsten');
  

  // we use ethers.Wallet to pass the privateKey and provider so we get access to the wallet
  const wallet = new ethers.Wallet(privateKey, provider);
	
  // ethers.Contract get 3 params in order to get access to the contract (contract address , abi("functions") , wallet)
  const contract = new ethers.Contract(CONTRACT_ADDRESS, contracte, wallet);
  
  // now we just call the function that's already declared in the abi
   const result = await contract.getMyActions();
    
	
  // just normal response
    res.json({ 
      message : 'successfully',
      status: result
     });
  }catch(e){
    res.json({ 
      message : 'fail',
      status: e.message
     });
  }
});

app.listen(port);


// addition notes:
// we used Heroku for host
// we got now.json for server ABI
// we just downloaded (ethers, express, nodemon(but globally so it will not be saved in our project))