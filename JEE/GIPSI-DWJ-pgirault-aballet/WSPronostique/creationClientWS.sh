wsdluri='http://localhost:9002/CalculEndpoint?wsdl'
gensrcdir='./src/main/java'
targetpackage=calcul.util.client
genoutdir='./target/classes'
wsimport -d "$genoutdir" -s "$gensrcdir" -p $targetpackage -keep "$wsdluri"
