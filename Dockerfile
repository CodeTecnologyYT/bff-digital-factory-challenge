FROM  eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/app.jar app.jar
# Copia el archivo de configuración si usas uno
COPY build/resources/main/applicationinsights-dev.json /app/applicationinsights-dev.json
# Variable obligatoria: tu Connection String
ENV APPLICATIONINSIGHTS_CONNECTION_STRING="InstrumentationKey=f7381b09-1700-4bd8-ac31-dd0aea0f64f0;IngestionEndpoint=https://westus2-2.in.applicationinsights.azure.com/;LiveEndpoint=https://westus2.livediagnostics.monitor.azure.com/;ApplicationId=0b72907d-9577-423d-ab69-1564da404b00"
# Si usas runtime attach, agrega la propiedad para el JSON
ENV APPLICATIONINSIGHTS_RUNTIME_ATTACH_CONFIGURATION_CLASSPATH_FILE="applicationinsights-dev.json"
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]