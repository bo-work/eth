//simple store
contract SimpleStorage{
	
	uint storedData;
	
	function set(uint x) public {
		storedData = ;
	}
	
	function get() public constant returns (uint){
	return storedData;
	}
}

//coin
contract Coin {
	
	address public minter;
	mapping (address => uint) public balance;
	
	event Sent(address from , address to , uint amount);
	
	function Coin() public {
		minter = msg.sender;
	}
	
	function send(address receiver , uint amount) public {
		if (balances[msg.sender]<amount) return;
		balances[msg.sender] -= amount;
		balances[receiver] += amount; 
		emit Sent(msg.sender, receiver,amout);
	}
    
    function mint(address receiver , uint amount) public {
		if (msg.sender != minter) return; 	
		balances[receiver] += amount;
	}
}
 
//transfer
pragma solidity ^0.4.4;

contract Token {
	address issuer;
	mapping(address =>uint) balance;
	
	event Issue(address account, uint amount);
	event Transfer(address issuer, address to,uint amount);
	 
	function Token() public {
		issuer = msg.sender;
	}
	
	function issue(address account, uint amount) public {
		require(msg.sender == account);		
		balance[account] += amount;
	}
	
	function transfer(address to, uint amount) public {
		require(balance[msg.sender] >= amount);
		balance[msg.sender] -= amount;
		balance[to] += amount;
		
		Transfer(msg.sender, to, amount);
	}
	
	function Find(address find) public view 
	returns (uint) {
		return  balance[find];
	}
}	

//transfer的编译：
var tokenContract = web3.eth.contract([{"constant":true,"inputs":[{"name":"find","type":"address"}],"name":"Find","outputs":[{"name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"name":"account","type":"address"},{"name":"amount","type":"uint256"}],"name":"issue","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"name":"to","type":"address"},{"name":"amount","type":"uint256"}],"name":"transfer","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"inputs":[],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"name":"account","type":"address"},{"indexed":false,"name":"amount","type":"uint256"}],"name":"Issue","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"name":"issuer","type":"address"},{"indexed":false,"name":"to","type":"address"},{"indexed":false,"name":"amount","type":"uint256"}],"name":"Transfer","type":"event"}]);
var token = tokenContract.new(
   {
     from: web3.eth.accounts[0], 
     data: '0x6060604052341561000f57600080fd5b336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506103b88061005e6000396000f300606060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063149a73761461005c578063867904b4146100a9578063a9059cbb146100eb575b600080fd5b341561006757600080fd5b610093600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061012d565b6040518082815260200191505060405180910390f35b34156100b457600080fd5b6100e9600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610176565b005b34156100f657600080fd5b61012b600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610201565b005b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b8173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156101b057600080fd5b80600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055505050565b80600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015151561024f57600080fd5b80600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000828254039250508190555080600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600082825401925050819055507fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef338383604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390a150505600a165627a7a72305820c387c4e8b3cf5b95c8355a5b3adaf2b8bb9cd1611a3381f7f8df21dba1ebdda30029', 
     gas: '4700000'
   }, function (e, contract){
    console.log(e, contract);
    if (typeof contract.address !== 'undefined') {
         console.log('Contract mined! address: ' + contract.address + ' transactionHash: ' + contract.transactionHash);
    }
 })
		
	
	


 


//vote 
contract Ballot{
	
	struct Voter {
		uint weight;
		bool voted;
		address delegate;
		uint vote;
	}
	
	struct Prorosal {
		bytes32 name;
		uint voteCount;
	}
	
	address public chairperson;
	mapping (address => Voter) public voters;
	Proposal[] public proposals;
	
	function Ballot (bytes32[] proposalNames) public{
		chairperson = msg.sender;
		voters[chairperson].weight = 1;
		for (uint i =0; i < proposalNames.length); i++) {
			proposals.push (Proposal({
				name: proposalNames[i],
				voteCount:0
			}));
		}
	} 
	
	function GiveRightToVote (address voter) public {
		require (
		(msg.sender == chairpersion)&& !=voters[voter].voted && (voters[voter].weight ==0)
		);
		voters[voter].weiht = 1;
	}
	
	function Delegate(address to) public {
		Voter storage sender = voters[msg.sender];
		require(!sender.voted);
		
		while (voters[to].delegate != address(0)){
			to = voters[to].delegate;
			require(to != msg.sender);
		}
		
		sender.voted = ture;
		sender.delegate = to;
		Voter storage delegate_ = voters[to];
		if (delegate_voted) {
			proposals[delegate_.vote].voteCount += sender.weight);
		}else{
			delegate_.weight += sender.weight;
		}
	}
	
	function vote(uint proposal) public {
		Voter storage sender = voters[msg.sender];
		require(!sender.voted);
		sender.voted = ture;
		sender.vote = proposal;
		
		proposals[proposal].voteCount += sender.weight;
	}
	
	function winningProposal() public view 
	returns (uint winningProposal_) {
		uint winningVoteCount = 0;
		for (uint p = 0; p < proposals.length; p++){
			if (proposals[p].voteCount > winningVoteCount){
				winningVoteCount = proposals[p].voteCount;
				winningProposal_ = p;
			}
		}
	}
	
	function winnerName() public view
	returns (bytes32 winnerName_) {
		winnerName_ = proposals[winningProposal()].name;
	}
}
		
				