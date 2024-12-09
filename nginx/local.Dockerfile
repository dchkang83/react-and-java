# Use the Nginx image from Docker Hub
FROM nginx:stable-alpine

# Remove the default Nginx configuration file
# RUN rm /etc/nginx/conf.d/default.conf

# COPY ./conf/conf.d/default.conf /etc/nginx/conf.d/default.conf