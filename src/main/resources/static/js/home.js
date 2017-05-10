(function($, exports) {
    var module = function() {
        this.elements = {};
        this.init();
    };

    module.fn = module.prototype;

    module.fn.init = function() {
        var self = this;
        self.bindEvents();
    };

    module.fn.bindEvents = function() {
        $(".submit-button").on("click", function() {
            $(".fastest-travel-data").removeClass("hidden");
            $.ajax({
              type: "POST",
              url: "/computeFastestFlight",
              data: {
                flightData : $(".flights-data").val(),
                origin: $(".origin").val(),
                destination: $(".destination").val()
              },
              success: function(data) {
                $(".fastest-travel-data").html(data);
              }
            });
        });

        $(".reset-button").on("click", function() {
            $(".fastest-travel-data").addClass("hidden");
            $(".flights-data").val("");
            $(".origin").val("");
            $(".destination").val("");
        });
    }

    exports.homeModule = module;
})($, window);

new homeModule();