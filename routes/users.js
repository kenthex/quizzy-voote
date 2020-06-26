const express = require('express');
const db = require('../config/database');
const sha256 = require('js-sha256');
const router = express.Router();
const User = require('../models/User');
const Token = require('../models/Token');

router.get('/', (req, res) => {
  User.findAll().
    then(users => {
      users.length ? res.send(users) : res.send('users table is empty');
    }).
    catch(err => console.log(err));});

router.get('/id/:id', (req, res) => {
  User.findOne({ where: { id: req.params.id }}).
    then(user => { user.length ? res.send("user doesn't exists") : res.send(user);}).
    catch(err => res.send(err)); });

router.get('/name/:name', (req, res) => {
  User.findOne({ where: { login: req.params.name }}).
  then(user => { user.length ? res.send("user doesn't exists") : res.send(user);}).
  catch(err => res.send(err)); });

router.post('/add', (req, res) => {
  console.log(req.body);
  if(req.body.password == req.body.repass) {
    User.create(req.body).
    then( () => res.send( req.body )).
    catch((err) => {
      console.log({ error: err.errors[0].message });
      res.send({ error: err.errors[0].message })

    });
  } else res.send({ error: "repass failed" });
});

router.post('/login', (req, res) => {
  var token = sha256("" + req.body.user_id + req.body.expired_at),
      currentDate = new Date(),
      date = currentDate.setDate(currentDate.getDate() + 1);
  req.body.expired_at = date;
  req.body.token = token;
  Token.create(req.body);
  res.send(req.body);
});

module.exports = router;
