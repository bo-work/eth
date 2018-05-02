# eth
创建私有链或者实验链
在目录文件mychain下
创建私有链
geth --identity "myfirstchain" init MyFirstGenesis,json --datadir "%cd%data"
启动私有链
geth --identity "myfirstchain" --datadir "%cd%\data" --networkid 123456 --rpc --rpcaddr="0.0.0.0" --rpccorsdomain "*" --port "30303" --rpcapi "db,eth,net,web3"  --rpcport "8200" --nodiscover console        (注意空格)
geth控制塔操作
密码123583
新shell衔接私有链
geth attach http://127.0.0.1:8200
新建账号
personal.newAccount()
查看账号
eth.accounts
设置别名
xxx=eth.account[]
节点信息
admin.nodeInfo
挖矿
miner.start()
miner.stop()
设置矿工地址
miner.setEtherbase(personal.listAccounts[0])
余额
eth.getBalance（）
转账
eth.sendTransaction({from:user1,to:user2,value:amount})
解锁
personal.unlockAccount(eth.accounts[0])
查询2 无转账
挖矿后 
查询2 有转账
