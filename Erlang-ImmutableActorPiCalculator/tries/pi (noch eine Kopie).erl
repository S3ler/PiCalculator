-module(pi).
-export([loop/0]).
-export([launch/1]).
% http://stackoverflow.com/questions/20339937/getting-result-of-a-spawned-function-in-erlang
% spawn with: PiElementActor = spawn(fun pi_element_actor:loop/0).
loop() ->
		receive
		% send: PiElementActor ! {self(), ElementNumber}.
		{From, ElementNumber} when erlang:is_integer(ElementNumber) ->
			Element = 4.0 * (1.0 - mod(ElementNumber,2) * 2.0) / (2.0 * ElementNumber + 1.0),
			io:format("Calculated~p~g~n",[self(),Element]),
			From ! {From, Element},
			loop();
		{'EXIT'} ->
			exit({pi,die,at,erlang:time()})
end.

mod(X,Y)->(X rem Y + Y) rem Y.

% launch Actors
launch(N) -> 
io:format("starting~n",[]),
launch(N,N)
.
launch(-1,N) -> collect(N);
launch(I,N) -> 
PiD = spawn(fun pi:loop/0),
io:format("spawning~p~n",[PiD]),
PiD ! {self(), I},
launch(I-1,N).




% collect result
collect(N) -> collect(N,0.0).
collect(-1,L) -> io:format("~g~n",[L]);
collect(N,L) ->
	receive
		{From, R} -> 
			From ! {'EXIT'},
			collect(N-1, L + R)
	end.
