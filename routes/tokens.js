const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Token = require('../models/Token');
const bodyParser = require('body-parser');

router.get('/', (req, res) => {
  res.send("INDEX TOKEN");
});

router.get('/:token', (req, res) => {
  Token.findAll({ where: { token: req.params.token }}).
    then( token => { res.send(token); }).
    catch(err => res.send(err));
});

module.exports = router;
