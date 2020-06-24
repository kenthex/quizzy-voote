const express = require('express');
const router = express.Router();
const db = require('../config/database');
const Token = require('../models/Token');
const bodyParser = require('body-parser');

router.get('/', (req, res) => {
  res.send("INDEX TOKENS");
});

router.get('/:token', (req, res) => {
  Token.findOne({ where: { token: req.params.token }}).
    then( token => {
      token.dataValues.expired_at = token.dataValues.expired_at.getTime();
      token.dataValues.createdAt = token.dataValues.createdAt.getTime();
      res.send(token.dataValues);
    }).catch(err => res.send(err));
});

router.delete('/:token', (req, res) => {
  console.log("trying delete token: " + req.params.token);
  Token.destroy({ where: { token: req.params.token } }, { force: true }).then(status => res.send({ status: "token deleted" }));
});

module.exports = router;
