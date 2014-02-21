(function(window) {

    var active = {};
    var keys = {
        "up": 38,
        "down": 40,
        "w": 87,
        "s": 83
    };
  var capture = {
    38: true,
    40: true,
    87: true,
    83: true,
  };

    var input = {

        key: function(k) {
            var cur = keys[k.name];                                   
            if(!cur) return false;
            return active[cur];
        }

    }

    $(document).bind("keydown", function(e) {
      if(capture[e.keyCode]) {
        e.preventDefault();
      }
      active[e.keyCode] = true;
    });

    $(document).bind("keyup", function(e) {
      if(capture[e.keyCode]) {
        e.preventDefault();
      }
      delete active[e.keyCode];
    });

    window.input = input;

})(window);