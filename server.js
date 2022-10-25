var mysql = require('mysql');
var express = require('express');
var bodyParser = require('body-parser');

var client = mysql.createConnection({
    user: 'root',
    password: 'Sjkim0208@^^@',
    database: 'Caza'
});


var app = express();
app.use(bodyParser.json());


//8000 포트로 서버 오픈


app.listen(8000, () => {
    console.log('server running at http://10.0.2.2:8000');
    app.use(express.static('public'));
});

//로그인/
app.get('/login', (req, res) => {
    var data = req.body;
      //데베 쿼리 실행
    if (data.flag == "client"){
        client.query('SELECT ID_CLIENT FROM client where name = ?', [data.id], function (error, results) {
            //응답
            res.send({"message": "로그인 성공", "success": "true", "status": "200"});
        });
    }
    else {
        client.query('SELECT ID_OWNER FROM owner where name = ?', [data.id], function (error, results) {
            //응답
            res.send({"message": "로그인 성공", "success": "true", "status": "200"});
         });
    }
});


//회원가입
app.post('/join', (req, res) => {
    var data = req.body;
      //데베 쿼리 실행
    if (data.flag == "client"){
        client.query('SELECT COUNT(*) AS COUNT FROM client where ID_CLIENT = ?', [data.id], function (error, results) {
            //응답
            if (results[0].COUNT=== 0) {
                client.query('INSERT INTO client (NAME_CLIENT, ID_CLIENT, PASSWORD_CLIENT, NUMBER_CLIENT) VALUES (?,?,?,?)', 
                [data.name, data.id, data.password, data.number], function (error, results){
                    console.log(results);
                    res.send({"message": "회원가입 성공", "success": "true", "status": "200"});
                });
            }
            else {res.send({"message": "회원가입 실패", "success": "false", "status": "404"});}
        });
    }
    else if (data.flag == "owner"){
        client.query('SELECT COUNT(*) AS COUNT FROM owner where ID_OWNER = ?', [data.id], function (error, results) {
            //응답
            if (results[0].COUNT === 0) {
                client.query('INSERT INTO owner (NAME_OWNER, ID_OWNER, PASSWORD_OWNER, NUMBER_OWNER) VALUES (?,?,?,?)', 
                [data.name, data.id, data.password, data.number], function (error, results){
                    res.send({"message": "회원가입 성공", "success": "true", "status": "200"});
                    console.log(results);
                });
            }
            else {res.send({"message": "회원가입 실패", "success": "false", "status": "404"});}
        });
    }
});

//회원정보 수정

//카페정보 추가
app.get('/addcafe', (req, res) => {
    var data = req.body;
      //데베 쿼리 실행
  /*  client.query('SELECT ID_OWNER FROM owner where ID_OWNER = ?',[data.id], function (error, results) {
        //응답
        res.send("true");
     });*/
    client.query('INSERT INTO cafe (ID_CAFE, NAME_CAFE, LOCATION, TIME, SEATS_TOTAL, MENU, HASHTAG, CONGESTION, DESCRIPTION, SEATS_FILLED, ID_OWNER) VALUES (?, ?, ?, ?, ?, ?, ?, 0, ?, 0, ?)', 
        [SUBSTR(MD5(RAND()),1,8), data.cafename, data.location, data.time, data.totalseats, data.menu, data.hashtag, data.description, data.id],function (error, results){
        //응답
        res.send({"message": "카페 추가 완료", "success": "true", "status": "200"});
    });
});

//카페정보 수정/수정중->results 를 다시 어플로 보내고 거기서 수정한것 다시 업뎃하게 만들기
app.get('/modifycafe', (req, res) => {
    var data = req.body;
      //데베 쿼리 실행
    client.query('SELECT * FROM cafe where ID_CAFE = ?',[data.cafeid], function (error, results) {
        //응답
       // console.log(results);
        //res.send("true");
     });
    client.query('UPDATE cafe SET NAME_CAFE = ?, LOCATION = ?, TIME = ?, SEATS_TOTAL = ?, MENU = ?, HASHTAG = ?, DESCRIPTION = ?) WHERE ID_OWNER = ?', 
        [data.cafename, data.location, data.time, data.totalseats, data.menu, data.hashtag, data.description, data.id],function (error, results){
        //응답
        res.send({"message": "카페 수정 완료", "success": "true", "status": "200"});
    });
});


//혼잡도 계산
app.get('/CONGESTION/:type', (req, res) => {
    var data = req.body;
    var seats_total = 0.0;
    var con = 0.0;
 
    client.query('SELECT SEATS_TOTAL FROM cafe where ID_OWNER = ?', [data.id], function (error, results) {
        seats_total = results[0].SEATS_TOTAL;
    });
    
    if (req.params.type === "plus"){
        var seats_filled = 0.0;
        client.query('SELECT SEATS_FILLED FROM cafe where ID_OWNER = ?', [data.id], function (error, results) {
            seats_filled = parseFloat(results[0].SEATS_FILLED);
            seats_filled = seats_filled + 1.0;
            con = seats_filled/seats_total;
            client.query('UPDATE cafe SET SEATS_FILLED = ? WHERE ID_OWNER = ?', [seats_filled, data.id], function (error, results) {
                client.query('UPDATE cafe SET congestion = ? WHERE ID_OWNER = ?', [con, data.id], function (error, results) {
                    res.send("true");
                });
            });
        });
    }
    else if (req.params.type === "minus"){
        var seats_filled = 0.0;
        client.query('SELECT SEATS_FILLED FROM cafe where ID_OWNER = ?', [data.id], function (error, results) {
            seats_filled = parseFloat(results[0].SEATS_FILLED);
            seats_filled = seats_filled - 1.0;
            con = seats_filled/seats_total;
            client.query('UPDATE cafe SET SEATS_FILLED = ? WHERE ID_OWNER = ?', [seats_filled, data.id], function (error, results) {
                client.query('UPDATE cafe SET congestion = ? WHERE ID_OWNER = ?', [con, data.id], function (error, results) {
                    res.send("true");
                });
            });
        });
    }
    else {
        var seats_filled = 0.0;
        client.query('SELECT SEATS_FILLED FROM cafe where ID_OWNER = ?', [data.id], function (error, results) {
            seats_filled = parseFloat(results[0].SEATS_FILLED);
            seats_filled = data.seats;
            con = seats_filled/seats_total;
            client.query('UPDATE cafe SET SEATS_FILLED = ? WHERE ID_OWNER = ?', [seats_filled, data.id], function (error, results) {
                client.query('UPDATE cafe SET congestion = ? WHERE ID_OWNER = ?', [con, data.id], function (error, results) {
                    res.send("true");
                });
            });
        });
    }
});

//지도에 카페 위치&혼잡도 보내기/
app.get('/main', (req, res)=>{
    //console.log("get http request");
    console.log(req.query);
    res.send("hello world!");
});

//카페정보 보내기