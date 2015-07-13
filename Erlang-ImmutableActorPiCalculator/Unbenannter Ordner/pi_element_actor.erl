-module(pi).
-export([loop/0]).
-export([loopw/1]).


% spawn with: PiElementActor = spawn(pi pi_element_actor:loop/0).
loop() ->
	receive
		% send: PiElementActor ! {self(), ElementNumber}.
		{From, Element} when  ->
 			Element = 4.0 * (1.0 - (ElementNumber % 2.0) * 2.0) / (2.0 * ElementNumber + 1.0).
			From ! Element.
			% exit({pi,die,at,erlang:time()});
		loop();

		{From, _} ->
			exit({pi,die,at,erlang:time()});
		loop()

end.

% spawn with: PiElementActor = spawn(pi, pi_element_actor:loop/1, self()).
loopw(From) ->
	receive
		{From, ) ->
 			Element = 4.0 * (1.0 - (ElementNumber % 2.0) * 2.0) / (2.0 * ElementNumber + 1.0).
			From ! Element.
		loop();

		_ ->
			exit({pi,die,at,erlang:time()});
		loop()

end.
