wsdluri='http://localhost:9001/PronostiqueEndpoint?wsdl'
gensrcdir='./src'
targetpackage=pronostic.util.client
genoutdir='./target/classes'
wsimport -d "$genoutdir" -s "$gensrcdir" -p $targetpackage -keep "$wsdluri"
