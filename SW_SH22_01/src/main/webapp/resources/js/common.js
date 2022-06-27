'use strict';

let isEmpty = function(value){
  if(value == null || value == "" || value == "null" || value == undefined){
    return true;
  }else{
    return false;
  }
};