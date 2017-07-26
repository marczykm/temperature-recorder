t = require("ds18b20")
pin = 4 

function cb(code, data)
  if(code < 0) then
    print('HTTP request failed')
  else
    print(code, data)
  end
end

function readout(temp)
  for addr, temp in pairs(temp) do
    http.post("http://192.168.0.101:5000/api/v1/temperature/"..temp, nil, cb)
  end
end

tmr.alarm(1,2000,tmr.ALARM_AUTO,function()
  t:readTemp(readout, pin)
end)
