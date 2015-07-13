-module(pi_element_actor).
-export([loop/0]).

% spawn with: PiElementActor = spawn(fun pi_element_actor:loop/0).
loop() ->
		receive
		% send: PiElementActor ! {self(), ElementNumber}.
		{From, ElementNumber} when erlang:is_integer(ElementNumber) ->
			Element = 4.0 * (1.0 - mod(ElementNumber,2) * 2.0) / (2.0 * ElementNumber + 1.0),
			From ! Element,
			loop();
		_ ->
			exit({pi,die,at,erlang:time()})
end.

mod(X,Y)->(X rem Y + Y) rem Y.
