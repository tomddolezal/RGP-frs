# RGP Fast Referral System
 
We are building a web application to allow doctors to quickly and efficiently refer older patients to the specialized geriatric services they require. Currently, doctors must manually find the best provider for their patient based on travel time, wait time, and the specific services they provide. Our product will streamline this process to allow doctors to find the best provider faster and with more accuracy. On top of that, we will help to automate the referal paperwork to increase responsiveness to the patients.

Our target demographic is doctors who handle older patients (> 65 years in age). Service providers will also be our users, as they must upload their wait times and will need to be able to access any referals that come from doctors.
 
 Currently, doctors essentially find a directory of all services in the area and must manually decide based on the distance to the patient. After this they must pull out a long form and fill it out for their specific patient in order to refer their patient to the services they require. Then they must fax this to the services they desire. We can automate a significant portion of this. First, we can make searching much faster by increasing the options in the search, filtering out undesirable services. Service providers will be able to upload their wait times, which will allow doctors to decrease load on a hospital and improve a patient's response time (by choosing the "faster" hospital). Doctors will also be able to quickly send referals since they no longer need to fill in each users information, since we can autofill it for them. This also helps patient information accuracy, since doctors can be error-prone. All this improves efficiency and allows doctors to do more important work.
 
 Current Mock up:
 https://projects.invisionapp.com/share/G8OFVLDWER2#/screens/325356722

## Execution instruction
### Backend
#### With Eclipse/IntelliJ
To execute backend, open the project with the IDE of your choice. Click `Run` on `Main`.

### Front-end
#### Setup
At `app` folder, run `npm i`.
#### Using Development Server
Run backend server with one of the above method. At `app` folder, run `npm start`.
#### Using Compiled Production Code
At `app` folder, run `npm run build`. Then start backend server using one of the above methods.